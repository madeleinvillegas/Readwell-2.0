from recommendations import critics
import recommendations
print(critics['Lisa Rose']['Lady in the Water'])
print(critics['Toby']['Snakes on a Plane'])
print(critics['Toby'])
print(recommendations.sim_distance(recommendations.critics, 'Lisa Rose','Gene Seymour'))
print(recommendations.sim_pearson(recommendations.critics, 'Lisa Rose','Gene Seymour'))
print(recommendations.getRecommendations(recommendations.critics,'Toby', similarity=recommendations.sim_distance))
movies=recommendations.transformPrefs(recommendations.critics)
print(recommendations.topMatches(movies,'Superman Returns'))
print(recommendations.getRecommendations(movies,'Just My Luck'))
itemsim=recommendations.calculateSimilarItems(recommendations.critics)
print(itemsim)
print(recommendations.getRecommendedItems(recommendations.critics,itemsim,'Toby'))