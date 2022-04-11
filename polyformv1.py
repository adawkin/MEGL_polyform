import numpy as np
from vpython import *
import pprint
import random

def createLongPolyform(n):
  piece = []
  for i in range(0, n):
    newPiece = (i, 0, 0) 
    if i == 0:
      piece = [newPiece]
    else:
      piece.append(newPiece)
  return piece

def isValidPolyform(polyform):

  strongly_connected = {polyform[0]}

  working_poly = polyform[1:]

  last_len = len(working_poly)
  while len(working_poly) > 0:
    for square in working_poly:
      
      possible_neighbors = [(square[0], square[1] + 1, square[2]), 
      (square[0], square[1] - 1, square[2]), 
      (square[0] + 1, square[1], square[2]), 
      (square[0] - 1, square[1], square[2]), 
      (square[0], square[1], square[2] + 1), 
      (square[0], square[1], square[2] - 1)]
      add = False

      for possible_neighbor in possible_neighbors:
        if possible_neighbor in strongly_connected:

          working_poly.remove(square)
          strongly_connected.add(square)
          break; 
    if last_len - len(working_poly) == 0:
      return False
    last_len = len(working_poly)

  return True

def get_perimeter(polyform):
  
  perimeter_blocks = set()

  for existing_square in polyform:

    candidates= {(existing_square[0], existing_square[1] + 1, existing_square[2]), 
    (existing_square[0], existing_square[1] - 1, existing_square[2]), 
    (existing_square[0] + 1, existing_square[1], existing_square[2]), 
    (existing_square[0] - 1, existing_square[1], existing_square[2]), 
    (existing_square[0], existing_square[1], existing_square[2] + 1), 
    (existing_square[0], existing_square[1], existing_square[2] - 1)}

    for possible_candidate in polyform:
      if possible_candidate in candidates:
        candidates.remove(possible_candidate)
    perimeter_blocks = perimeter_blocks.union(candidates)

  return perimeter_blocks

def shuffle(polyform):
  removed_index = random.randrange(0, len(polyform))
  removed_square = polyform.pop(removed_index) 

  possible_replacement_spots = list(get_perimeter(polyform))
  replacement_spot = random.randrange(0, len(possible_replacement_spots))

  polyform.append(possible_replacement_spots[replacement_spot])

  if not isValidPolyform(polyform):

    polyform.pop()
    polyform.append(removed_square)

  return polyform

def simShuffle(polyform, n):
  while n > 0:
    polyform = shuffle(polyform)
    n -= 1

  return polyform

def drawPolyform(polyform):

    count = 0

    for point in polyform:
        pos_i = vector(point[0], point[1], point[2])
        if count % 2 == 0:
            col_i = vector(1, 1, 0)
        elif count % 2 == 1:
            col_i = vector(0, 1, 0)
        
        box(pos = pos_i, 
            size = vector(1, 1, 1),
            color = col_i)
        count = count + 1

if __name__=="__main__":
  n = int(input("Length of Polyform: "))
  poly1 = createLongPolyform(n)

  sim = int(input("Number of Sims: "))
  poly1 = simShuffle(poly1, sim)
  print(poly1)

  print = input("Do you want to draw the polyform?(y/n): ")
  if print =="y" or print =="yes":
      drawPolyform(poly1)