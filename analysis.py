#Three lines to make our compiler able to draw:
import sys
import matplotlib
matplotlib.use('Agg')

import matplotlib.pyplot as plt
import numpy as np

y = np.array([5000, 25000, 5000, 5000])
mylabels = ["Expenses - 5000", "Income - 25000", "Mutual Funds - 5000", "Loan - 5000"]

plt.pie(y, labels = mylabels)
plt.legend(title = "Analysis Report")
plt.show() 

#Two  lines to make our compiler able to draw:
plt.savefig(sys.stdout.buffer)
sys.stdout.flush()
