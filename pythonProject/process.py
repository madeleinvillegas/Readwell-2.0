import csv
import time
# Import the beautifulsoup
# and request libraries of python.
import requests
from bs4 import BeautifulSoup as bs
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
path = "chromedriver_win32\chromedriver.exe"
driver = webdriver.Chrome(path)

data = []

driver.get("https://www.goodreads.com")
email = driver.find_element_by_id("userSignInFormEmail")
password = driver.find_element_by_id("user_password")
email.send_keys("edric.hao@gmail.com")
password.send_keys("Carls3n_N0_1")
password.send_keys(Keys.RETURN)

with open("BX-CSV-Dump\BX-Book-Ratings.csv", "r") as file:
    csv_reader = csv.reader(file)
    i=0
    for row in csv_reader:
        if i==0:
            i+=1
            continue
        data = row[0].split(";")
        isbn = data[1].strip("\"\"")
        search = driver.find_element_by_name("q")
        search.send_keys(isbn)
        search.send_keys(Keys.RETURN)
        title = driver.find_element_by_id("bookTitle")
        print(title.text)
        authors = driver.find_elements_by_class_name("authorName")
        for author in authors:
            print(author.text)
        text = driver.find_elements_by_class_name("row")
        published = text[1].text
        print(published)
        text = driver.find_elements_by_xpath("//span[@itemprop='numberOfPages']")
        pages = text[0].text
        print(pages)
        text = driver.find_elements_by_xpath("//div[@itemprop='inLanguage']")
        language = text[0].text
        print(language)
        description = driver.find_elements_by_xpath("//div[@id='description']")

time.sleep(10)
driver.quit()

