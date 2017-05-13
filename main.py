import csv
import os
import matplotlib.pyplot as plt
import numpy as np
import datetime
from matplotlib import style

style.use('ggplot')

dir = os.path.dirname(os.path.realpath(__file__))

price_dir = os.path.realpath(dir + '/dataset')

files = []

for f in os.listdir(price_dir):
    if '_prices.csv' in f:
        files.append(price_dir + "\\" + f)

print(datetime.datetime.strptime('2017-03-15 23:15:04', '%Y-%m-%d %H:%M:%S'))

# print(
#     [datetime.datetime.strptime('2017-03-04 00:00:01', '%Y-%m-%d %H:%M:%S'), 1],
#     [datetime.datetime.strptime('2017-03-04 00:00:02', '%Y-%m-%d %H:%M:%S'), 2],
#     [datetime.datetime.strptime('2017-03-04 00:00:03', '%Y-%m-%d %H:%M:%S'), 3],
#     [datetime.datetime.strptime('2017-03-04 00:00:04', '%Y-%m-%d %H:%M:%S'), 4]
# )
#
# plt.plot([
#     [datetime.datetime.strptime('2017-03-04 00:00:01', '%Y-%m-%d %H:%M:%S'), 1],
#     [datetime.datetime.strptime('2017-03-04 00:00:02', '%Y-%m-%d %H:%M:%S'), 2],
#     [datetime.datetime.strptime('2017-03-04 00:00:03', '%Y-%m-%d %H:%M:%S'), 3],
#     [datetime.datetime.strptime('2017-03-04 00:00:04', '%Y-%m-%d %H:%M:%S'), 4]
# ])
# plt.show()

for f in files:
    time = []
    data = []
    with open(f) as csvfile:
        reader = csv.reader(csvfile, delimiter=',')
        i = 1
        for row in reader:
            if row[0] != 'times':
                # time.append(datetime.datetime.strptime(row[0], '%Y-%m-%d %H:%M:%S'))
                time.append(i)
                data.append(float(row[1]))
                i += 1

    # plt.plot_date(time, data, linewidth=0.1)
    plt.plot(time, data)

plt.show()

# i = 1
# for f in files:
#     time = []
#     data = []
#     figure = plt.figure()
#     with open(f) as csvfile:
#         reader = csv.reader(csvfile, delimiter=',')
#         for row in reader:
#             if row[0] != 'times':
#                 time.append(datetime.datetime.strptime(row[0], '%Y-%m-%d %H:%M:%S'))
#                 data.append(float(row[1]))
#
#     x = figure.add_subplot(111)
#     x.plot_date(time, data)
#     ++i
#
# plt.show()
#
