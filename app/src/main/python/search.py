import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
from os.path import dirname, join

def tf_idf(search_keys, dataframe, label):
    tfidf_vectorizer = TfidfVectorizer()
    tfidf_weights_matrix = tfidf_vectorizer.fit_transform(dataframe.loc[:, label])
    search_query_weights = tfidf_vectorizer.transform([search_keys])

    return [search_query_weights, tfidf_weights_matrix]

def most_similar(similarity_list, min_talks=1):
    most_similar = []

    while min_talks > 0:
        tmp_index = np.argmax(similarity_list)
        most_similar.append(tmp_index)
        similarity_list[tmp_index] = 0
        min_talks -= 1

    return most_similar

def cos_similarity(search_query_weights, tfidf_weights_matrix):
    cosine_distance = cosine_similarity(search_query_weights, tfidf_weights_matrix)
    similarity_list = cosine_distance[0]

    return similarity_list


def load_data(search):
    dataframe = pd.read_csv(join(dirname(__file__), "data.csv"))
    data = []
    for row in dataframe.itertuples():
        document = ""
        document += row[2]
        document += " "
        document += row[3].replace(";", " ")
        document += row[9].replace("\"\"", " ").replace("-", " ").replace(".", " ").replace("?", " ").replace("—", " ")
        document += " "
        document += row[8]

        punctuations = '''!()-[]{};:'"\,<>./?@#$%^&*_~'''

        no_punct = ""
        for char in document:
            if char not in punctuations:
                no_punct = no_punct + char
        data.append(no_punct)
    data = pd.DataFrame(data, columns = ["Books"])
    matrix = tf_idf(search, data, "Books")
    similar = cos_similarity(matrix[0], matrix[1])
    results = most_similar(similar, 3)
    books = []
    for result in results:
        if result != 0:
            books.append(dataframe.iloc[result].tolist()[1:])
    return books

print(load_data("Harry"))






