from __future__ import division  # Only for how I'm writing the transition matrix
import networkx as nx  # For the magic
import matplotlib.pyplot as plt
import numpy as np


def polycoordinates(n): #polyomino of length n
    graph = {} #dict of possible adjacent values
    length = n - 2

    #set up row 1, so we start in the bottom left as (0, 0)
    for x in range(0, n):
      graph[(x, 0)] = []
    
    for y in range(1, n):
        for x in range(-length, length + 1):
            graph[(x, y)] = [] 
        length -= 1
    
    for i in graph:
        up = (i[0], i[1] + 1)
        down = (i[0], i[1] - 1)
        right = (i[0] + 1, i[1])
        left = (i[0] - 1, i[1])
        if right in graph:
            graph[i].append(right) 
        if up in graph:
            graph[i].append(up)
        if left in graph:
            graph[i].append(left)
        if down in graph:
            graph[i].append(down)
    return graph 

states = polycoordinates(3)
states = list(states.keys())




G = nx.MultiDiGraph()
labels={}
edge_labels={}

for i, origin_state in enumerate(states):
    for j, destination_state in enumerate(states):
        rate = Q[i][j]
        if rate > 0:
            G.add_edge(origin_state,
                       destination_state,
                       weight=rate,
                       label="{:.02f}".format(rate))
            edge_labels[(origin_state, destination_state)] = label="{:.02f}".format(rate)

plt.figure(figsize=(14,7))
node_size = 200
pos = {state:list(state) for state in states}
nx.draw_networkx_edges(G,pos,width=1.0,alpha=0.5)
nx.draw_networkx_labels(G, pos, font_weight=2)
nx.draw_networkx_edge_labels(G, pos, edge_labels)
plt.axis('off')

plt.show()