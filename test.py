import csv
import os
import matplotlib.pyplot as plt
import numpy as np
import datetime
from matplotlib import style
from sklearn import preprocessing
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression

style.use('ggplot')

dir = os.path.dirname(os.path.realpath(__file__))

price_dir = os.path.realpath(dir + '/dataset')

files = []

for f in os.listdir(price_dir):
    if '_prices.csv' in f:
        files.append(price_dir + "\\" + f)

for f in files:
    i = 1
    time = []
    data = []
    with open(f) as csvfile:
        reader = csv.reader(csvfile, delimiter=',')
        for row in reader:
            if row[0] != 'times':
                time.append(i)
                data.append(float(row[1]))
                i += 1
    plt.plot(time, data)

plt.show()
