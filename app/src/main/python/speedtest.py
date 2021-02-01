import pickle
a_file = open("data.pkl", "rb")
output = pickle. load(a_file)
print(output)
a_file. close()