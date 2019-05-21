#Sudoku Solver
#Constraint Satisfaction Problems
#Max Kramer, Declan Galleher

import sys
import queue
import copy
import time

adjList = {} #key: cell, value: every connected cell

class CSP(): #problem class - contains cells,domains,adjList representation of edges
    def __init__(self,puzzle): #constructor: instantiate a CSP
        self.cells = [["." for y in range(9)] for x in range(9)] #list of all cells (row,column)
        self.domains = {} #key: cell, value: domain for cell
        self.solved = {} #key: cell, value: solution for cell
        self.numFreq = {} #key: number, value: frequency it shows up in domains
        self.constructPuzzle(puzzle) #call construction function

    def constructPuzzle(self,puzzle): #constructor for CSP elements
        puzzlePos = 0
        for num in range(9):
            self.numFreq[num+1] = 0
        for x in range(9): #for every row
            for y in range(9): #for every column
                #CELLS#
                self.cells[x][y] = puzzle[puzzlePos] #create a sub-list for each row of the problem

                #DOMAINS#
                if self.cells[x][y] == ".": #if not assigned
                    self.domains[(x,y)] = [1,2,3,4,5,6,7,8,9] #set domain as 1-9
                    for num in range(9):
                        self.numFreq[num+1] += 1
                    self.solved[(x,y)] = '.'
                else: #if filled
                    self.domains[(x,y)] = [int(self.cells[x][y])] #set domain as value
                    self.solved[(x,y)] = int(self.cells[x][y])


                puzzlePos += 1 #increment row
        #print("cells, domains, and adjLists created")

    def enterValue(self, cell, val): #enters a value into a cell
        self.domains[cell] = [val]
        self.solved[cell] = val
        for num in range(9):
            self.numFreq[num+1] -= 1

def printMatrix(CSP): #return graphic of board
    line = ""
    for row in range(9): #for every row
        i = 0
        if row%3 == 0:
            line = line + " ––––––––––– \n"
        for column in range(9): #split along columns
            if i%3 == 0:
                line = line + "|"
            value = CSP.solved[(row, column)]
            line = line + str(value)
            i+=1
        line = line + "|\n" #next row

    print(line)

def printCSP(CSP): #print out final answer
    line = ""
    for row in range(9): #for every row
        i = 0
        for column in range(9): #split along columns
            value = CSP.solved[(row, column)]
            line = line + str(value)
            i+=1
    print(line)

def queuePrint(q): #for testing - print out queue
    print("Queue:")
    q2 = queue.Queue()
    while not q.empty():
        head = q.get()
        print(head)
        q2.put(head)
    return q2

def revise(CSP,xi,xj): #Test constraints for an arc
    revised = False #set revised to false initially
    for x in CSP.domains[xi]: #for every value in the domain of vertex x
        satisfied = True#False
        if len(CSP.domains[xj]) == 1 and CSP.domains[xj][0] == x: #if x violates the constraints of sudoku
            satisfied = False #set satisfied to false
        #for y in CSP.domains[xj]: #this is the version from the slides, it works as well, just start with satisfied as false
        #    if x != y: #if value satisfies constraints
        #        satisfied = True
        if not satisfied: #if value does not satisfy constraints
            CSP.domains[xi].remove(x) #prune the value from the domain of vertex x
            CSP.numFreq[x] -= 1
            if len(CSP.domains[xi]) == 1: #if the domain of Xi is only 1 number
                CSP.solved[xi] = CSP.domains[xi][0] #add solution
                CSP.numFreq[CSP.solved[xi]] -= 1
            revised = True #set revised to true
    return revised

def ac3(CSP): #Test constraints for all arcs in CSP
    #print("entered ac3")
    arcs = queue.Queue() #create a queue of arcs
    for i in adjList: #for every cell's adjList
        for j in adjList[i]: #for every value in adjList of Xi
            if i != j: #excluding arcs from Xi to Xi
                arcs.put((i,j)) #load all arcs in puzzle onto arcs list

    while not arcs.empty():
        arc = arcs.get() #get next arc as a tuple
        c1 = arc[0] #get first node in arc
        c2 = arc[1] #get second node in arc
        if revise(CSP, c1, c2):
            if len(CSP.domains[c1]) == 0: #if domain is empty
                return False #no valid assignment
            for cell in adjList[c1]: #for all neighbors of Xi (Propogate)
                if cell != c2: #EXCEPT Xj
                    arcs.put((cell, c1)) #enqueue into arcs
    return True

def ac3BackTrack(CSP, var): #Test constraints for arcs from cell var
    #print("entered ac3")
    arcs = queue.Queue() #create a queue of arcs
    for neighbor in adjList[var]: #for every value in adjList of var
        if neighbor != var: #excluding arcs from Xi to Xi
            arcs.put((var,neighbor)) #load all arcs from var onto arcs queue
            arcs.put((neighbor, var)) #load all arcs to var onto arcs queue

    while not arcs.empty():
        arc = arcs.get() #get next arc as a tuple
        c1 = arc[0] #get first node in arc
        c2 = arc[1] #get second node in arc
        if revise(CSP, c1, c2):
            if len(CSP.domains[c1]) == 0: #if domain is empty
                return False #no valid assignment
            for cell in adjList[c1]: #for all neighbors of Xi (Propogate)
                if cell != c2: #EXCEPT Xj
                    arcs.put((cell, c1)) #enqueue into arcs
    return True

def selectUnassignedVariable(CSP): #MRV heuristic with random selection for multiples
    var = None #MRV value
    varLength = 10 #cannot exceed domain size of 9
    for cell in CSP.domains: #find cell with size below varlength
        if len(CSP.domains[cell]) > 1 and len(CSP.domains[cell]) < varLength:
            var = cell #update MRV value
            varLength = len(CSP.domains[var]) #update MRV length
    return var

def orderValues(CSP, var): #Optimizer, order values based on frequency
    order = []
    for num in CSP.domains[var]: #loop through domain for var
        order.append((CSP.numFreq[num], num)) #add tuple of value and frequency to list
    return sorted(order)#return list sorted by second value in tuple

def complete(CSP): #Test if solution is complete
    comp = True
    for cell in CSP.solved: #check every cell that has been solved
        if CSP.solved[cell] == '.': #if cell is not filled
            comp = False #solution is not complete
    return comp

def backtrack(CSP): #solve the CSP
    if complete(CSP): #if assignment is complete, return the completed CSP
        return CSP
    var = selectUnassignedVariable(CSP) #get next cell to fill

    for item in CSP.domains[var]:#orderValues(CSP, var): #test value based on optimizer
        conflict = False
        val = item#[1]#use 1 index if using orderValues
        for cell in adjList[var]: #search adjList for cell
            if CSP.solved[cell] == val: #if assignment is not consistent
                conflict = True
        if not conflict: #if assignment is consistent
            newCSP = copy.deepcopy(CSP)#make a deep copy of
            newCSP.enterValue(var, val)
            if ac3BackTrack(newCSP, var): #use AC3 to test for null domains
                result = backtrack(newCSP) #recurse if AC3
                if result != "failure":
                    return result
    return "failure"

def Main(): #read in the file and call the other functions
    startTime = time.process_time()
    problemList = []
    file = open(sys.argv[1]) #read in file
    print("file read in successfully")
    for line in file: #loop over lines
        problemList.append(line) #store puzzles in a list
    print("Puzzles stored")

    #Making the adjacency dictionary
    for x in range(9):
        for y in range(9):
            #ADJACENCY LISTS#
            edges = set() #create empty adjacency list

            for i in range(9): #add in all vertices in the same column
                colCell = (x,i)
                if colCell not in edges and i != y: #skip duplicate entries
                    edges.add(colCell)

            for i in range(9): #add in all vertices in same row
                rowCell = (i,y)
                if rowCell not in edges and i != x: #skip duplicate entries
                    edges.add(rowCell)

            #HANDLE EACH "SQUARE"#
            xSquarePos = x//3 #assign x coord to one of three X squares
            ySquarePos = y//3 #assign y coord to one of three Y squares

            for i in range(3): #for every x in square
                for j in range(3): #for every y in square
                    sqrCell = (i+3*xSquarePos,j+3*ySquarePos) #add each value in square to adjList
                    if sqrCell not in edges and sqrCell != (x,y):
                        edges.add(sqrCell)
            adjList[(x,y)] = edges #load adjList to dictionary

    for i in range(len(problemList)): #iterate over all puzzles in file
        puzzle = problemList[i] #get next puzzle from file
        #print(puzzle)
        csp = CSP(puzzle) #create a CSP from the problem
        ac3(csp)
        if not complete(csp):
            solved = backtrack(csp)
        else:
            solved = csp
        if solved == "failure":
            print(solved)
        else:
            #print("solution found: ", complete(solved))
            printCSP(solved)
    endTime = time.process_time()
    duration = endTime - startTime
    print("Time to run: ", duration)
Main() #Run
