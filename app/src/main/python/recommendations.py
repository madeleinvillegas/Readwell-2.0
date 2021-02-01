import pandas as pd

from os.path import dirname, join

def initialize():
    reviewers = {}

    df = pd.read_csv(join(dirname(__file__), "ratings/ratings.csv"))
    i = 0
    for row in df.itertuples():
        if i == 0:
            i += 1
            continue
        if not int(row[1]) <= 45 and int(row[1]) not in [5702, 6677, 6966, 9032, 9666]:
            continue
        if int(row[2]) not in reviewers.keys():
            reviewers.update({int(row[2]): {}})
            reviewers[int(row[2])].update({int(row[1]): int(row[3])})
        else:
            reviewers[int(row[2])].update({int(row[1]): int(row[3])})
        i += 1
    return reviewers




def sim_distance(prefs, person1, person2):
    # Get the list of shared_items
    si = {}
    for item in prefs[person1]:
        if item in prefs[person2]:
            si[item] = 1
    # if they have no ratings in common, return 0
    if len(si) == 0: return 0
    # Add up the squares of all the differences
    sum_of_squares = sum([pow(prefs[person1][item] - prefs[person2][item], 2)
                          for item in prefs[person1] if item in prefs[person2]])
    return 1 / (1 + sum_of_squares)

def sim_pearson(prefs,p1,p2):
    # Get the list of mutually rated items
    si={}
    for item in prefs[p1]:
        if item in prefs[p2]: si[item]=1
        # Find the number of elements
        n=len(si)
        # if they are no ratings in common, return 0
    if n==0: return 0
    # Add up all the preferences
    sum1=sum([prefs[p1][it] for it in si])
    sum2=sum([prefs[p2][it] for it in si])
    # Sum up the squares
    sum1Sq=sum([pow(prefs[p1][it],2) for it in si])
    sum2Sq=sum([pow(prefs[p2][it],2) for it in si])
    # Sum up the products
    pSum=sum([prefs[p1][it]*prefs[p2][it] for it in si])
    # Calculate Pearson score
    num=pSum-(sum1*sum2/n)
    den=((sum1Sq-pow(sum1,2)/n)*(sum2Sq-pow(sum2,2)/n))**(1/2)
    if den==0: return 0
    r=num/den
    return r

def topMatches(prefs,person,n=5,similarity=sim_pearson):
    scores=[(similarity(prefs,person,other),other)
    for other in prefs if other!=person]
    # Sort the list so the highest scores appear at the top
    scores.sort( )
    scores.reverse( )
    return scores[0:n]

# Gets recommendations for a person by using a weighted average
# of every other user's rankings
def getRecommendations(prefs,person,similarity=sim_pearson):
    totals={}
    simSums={}
    for other in prefs:
        # don't compare me to myself
        if other==person: continue
        sim=similarity(prefs,person,other)
            # ignore scores of zero or lower
        if sim<=0: continue
        for item in prefs[other]:
            # only score movies I haven't seen yet
            if item not in prefs[person] or prefs[person][item]==0:
                # Similarity * Score
                totals.setdefault(item,0)
                totals[item]+=prefs[other][item]*sim
                # Sum of similarities
                simSums.setdefault(item,0)
                simSums[item]+=sim
    # Create the normalized list
    rankings=[(total/simSums[item],item) for item,total in totals.items( )]
    # Return the sorted list
    rankings.sort()
    rankings.reverse()
    return rankings
def transformPrefs(prefs):
    result={}
    for person in prefs:
        for item in prefs[person]:
            result.setdefault(item,{})
            # Flip item and person
            result[item][person]=prefs[person][item]
    return result

def calculateSimilarItems(prefs,n=10):
    # Create a dictionary of items showing which other items they
    # are most similar to.
    result={}
    # Invert the preference matrix to be item-centric
    itemPrefs=transformPrefs(prefs)
    c=0
    for item in itemPrefs:
        # Status updates for large datasets
        c+=1
        if c%100==0: print("%d / %d" % (c,len(itemPrefs)))
        # Find the most similar items to this one
        scores=topMatches(itemPrefs,item,n=n,similarity=sim_distance)
        result[item]=scores
    return result

def getRecommendedItems(prefs,itemMatch,user):
    userRatings=prefs[user]
    scores={}
    totalSim={}
    # Loop over items rated by this user
    for (item,rating) in userRatings.items( ):
        # Loop over items similar to this one
        for (similarity,item2) in itemMatch[item]:
            # Ignore if this user has already rated this item
            if item2 in userRatings: continue
            # Weighted sum of rating times similarity
            scores.setdefault(item2,0)
            scores[item2]+=similarity*rating
            # Sum of all the similarities
            totalSim.setdefault(item2,0)
            totalSim[item2]+=similarity
    # Divide each total score by total weighting to get an average
    rankings=[(score/totalSim[item],item) for item,score in scores.items( )]
    # Return the rankings from highest to lowest
    rankings.sort()
    rankings.reverse()
    return rankings

def getSimilarItems(item):
    reviews = initialize()
    itemsim = calculateSimilarItems(reviews, 3)
    recommendations = itemsim.get(item)
    items = []
    for recommendation in recommendations:
        items.append(recommendation[1])
    return items


def process():
    df = pd.read_csv(join(dirname(__file__), "data.csv"))
    data = []
    for row in df.itertuples():
        print(row[2])
        book = []
        items = getSimilarItems(int(row[1]))
        book.append(str(row[2]))
        book.append(str(row[3]))
        book.append(str(row[5]))
        book.append(str(row[7]))
        book.append(str(row[4]))
        book.append(str(row[6]))
        book.append(str(row[9]))
        book.append(str(row[8]))
        book.append(str(row[10]))
        for row2 in df.itertuples():
            if int(row2[1]) in items:
                book.append(row2[2])
        data.append(book)
    save = pd.DataFrame(data)
    save.to_csv('preprocess.csv', header=None, index=False)

def getBooks():
    books = pd.read_csv(join(dirname(__file__), "preprocess.csv"))
    books = books.values.tolist()
    return books


process()