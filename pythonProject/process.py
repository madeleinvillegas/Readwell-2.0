import csv
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

path = "chromedriver_win32\chromedriver.exe"

options = Options()
options.add_argument("--disable-blink-features=AutomationControlled")
options.add_argument('--enable-print-browser')
options.add_argument("--headless")
driver = webdriver.Chrome(path, options=options)


data = []

driver.get("https://www.goodreads.com")
email = driver.find_element_by_id("userSignInFormEmail")
password = driver.find_element_by_id("user_password")
email.send_keys("edric.hao@gmail.com")
password.send_keys("Carls3n_N0_1")
password.send_keys(Keys.RETURN)

titles = []
id = []

with open("books\\books.csv", "r", encoding="utf8") as file:
    csv_reader = csv.reader(file)
    i=0
    for row in csv_reader:
        if i==0:
            i+=1
            continue
        if i<=45 or i in [5702, 6677, 6966, 9032, 9666]:
            id.append(i)
            titles.append(row[10])
        i+=1

with open("data.csv", "w", newline="") as file:
    csv_writer = csv.writer(file)
    i = 0
    for title in titles:
        try:
            print(i)
            datum = [id[i]]
            search = driver.find_element_by_name("q")
            search.send_keys(title)
            search.send_keys(Keys.RETURN)
            book = driver.find_element_by_class_name("bookTitle")
            driver.get(book.get_attribute("href"))
            element_present = EC.presence_of_element_located((By.CLASS_NAME, 'responsiveSiteFooter__linkListItem'))
            WebDriverWait(driver, 5).until(element_present)
            title = driver.find_element_by_id("bookTitle")
            datum.append(title.text)
            authors = driver.find_elements_by_class_name("authorName")
            names = ""
            for author in authors:
                names = author.text + ";"
            datum.append(names)
            text = driver.find_elements_by_class_name("row")
            published = text[1].text.strip("Published ").split(" by ")
            datum.append(published[0])
            datum.append(published[1])
            text = driver.find_elements_by_xpath("//span[@itemprop='numberOfPages']")
            pages = text[0].text
            datum.append(pages)
            text = driver.find_elements_by_xpath("//div[@itemprop='inLanguage']")
            language = text[0].text
            datum.append(language)
            try:
                button = driver.find_element_by_xpath("//a[@onclick='swapContent($(this));; return false;']")
                button.click()
            except:
                pass
            description = driver.find_element_by_xpath("//div[@id='description']")
            datum.append(description.text.strip("(less)").replace("\n", "").replace("\t", "").replace("\r", ""))
            try:
                genre = driver.find_element_by_xpath("//a[@class='actionLinkLite bookPageGenreLink']")
                datum.append(genre.text)
            except:
                datum.append("None")
            rating = driver.find_element_by_xpath("//span[@itemprop='ratingValue']")
            datum.append(rating.text)
            print(rating.text)
            csv_writer.writerow(datum)

        except Exception as e:
            print(e)
        i+=1

