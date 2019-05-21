#Assignment 2 - Search for a Restuarant
#Max Kramer
#I affirm that I have adhered to the honor code on this assignment

import map
from problem import Problem
import sys
import time
from queue import Queue
from queue import PriorityQueue

def BFS(problem):
    nodesExpanded = 0
    parent = {}
    pathlength = 0
    frontier = Queue() #create frontier queue
    exploredSet = {} #create explored set
    frontier.put((0,problem.startState())) #add root to frontier
    exploredSet[problem.startState()] = True #add the head's state to explored
     
    while not frontier.empty():
        active = frontier.get() #get head of frontier queue
        nodesExpanded = nodesExpanded + 1 #add to nodes expanded
        #print("Visiting",  active[1], "cost", active[0]) 
        
        for state in problem.actions(active[1]): #Add the head's children to the tree
            
            if problem.goal(state) : # if a child is a goal, return
                parent[state] = active[1]
                print("")
                print("total path cost with BFS", active[0] + problem.cost(active[1],state))
                print("BFS goal reached: ", state)
                print("nodes expanded with BFS: " , nodesExpanded)
                return True
            
            if state not in exploredSet:
                frontier.put((active[0] + problem.cost(active[1],state), state))
                parent[state] = active[1]
                exploredSet[active[1]] = True #add the head's state to explored
            
            
def UCS(problem):
    nodesExpanded = 0
    parent = {}
    frontier = PriorityQueue() #create frontier priority queue
    exploredSet = {} #create explored set
    frontier.put((0,problem.startState())) #add root to frontier at front of priority queue

    while not frontier.empty():
        active = frontier.get() #get head of frontier
        if active[1] not in exploredSet: #check for duplicate expansions
            #print("visiting",active[1],"cost", active[0])
            exploredSet[active[1]] = True
            nodesExpanded = nodesExpanded + 1 #add to nodes expanded
            
            if problem.goal(active[1]): #if the head is a goal, return
                    parent[state] = active[1]
                    print("total path cost with UCS: ", active[0])
                    print("UCS goal reached: ", active[1])
                    print("nodes expanded with UCS: " , nodesExpanded - 1)
                    return True
            
            for state in problem.actions(active[1]): #add children
                if state not in exploredSet:
                    frontier.put((active[0] + problem.cost(active[1],state), state)) 
            
            
def aStar(problem):
    
    def heuristic(a): #Manhattan distance heuristic
        smallest = 9999999
        for b in problem.goalStates():    
            x1, y1 = a
            x2, y2 = b
            dist = abs(x1 - x2) + abs(y1 - y2)
            if dist < smallest:
                smallest = dist
        return smallest
    
    parent = {}
    nodesExpanded = 0
    frontier = PriorityQueue() #create frontier priority queue
    exploredSet = {} #create explored set
    frontier.put((0,0,problem.startState())) #add root to frontier at front of priority queue
    
    while not frontier.empty():
        active = frontier.get() #get head of frontier
        if active[2] not in exploredSet: #check for duplicate expansions
            #print("visiting",active[2],"cost w/heuristic", active[0], "base cost", active[1])
            exploredSet[active[2]] = True
            nodesExpanded = nodesExpanded + 1 #add to nodes expanded
            
            if problem.goal(active[2]): #if the head is a goal, return
                    parent[state] = active[2]
                    print("total path cost with A*: ", active[1])
                    print("A* goal reached: ", active[2])
                    print("nodes expanded with A*: " , nodesExpanded - 1)
                    return True
            
            for state in problem.actions(active[2]): #add children
                if state not in exploredSet:
                    tup = (active[1] + problem.cost(active[2],state) + heuristic(state), active[1] + problem.cost(active[2], state), state) 
                    frontier.put(tup)    

def Main():
    myMap = map.readFromFile(sys.argv[1])
    print("Map:", sys.argv[1])
    print("Map read in successfully")
    p = Problem(myMap)
    startTime = time.process_time()
    BFS(p)
    endTime = time.process_time()
    duration = endTime - startTime
    print("time spent with BFS: ",duration, "seconds")
    print("BFS completed successfully")
    print("")
    startTime = time.process_time()
    UCS(p)
    endTime = time.process_time()
    duration = endTime - startTime
    print("time spent with UCS: ",duration,"seconds")
    print("UCS completed successfully")
    print("")
    startTime = time.process_time()
    aStar(p)
    endTime = time.process_time()
    duration = endTime - startTime
    print("time spent with A*: ",duration,"seconds")
    print("A* completed successfully")

Main()
