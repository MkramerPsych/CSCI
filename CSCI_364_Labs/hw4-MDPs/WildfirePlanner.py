#Wildfireplanner.py
#Declan Galleher and Max Kramer

import sys
import math

class State(): #Define state class
    def __init__(self,num,x,y,F0,F1,F2,F3): #constructor
        self.num = num
        self.x = x
        self.y = y
        self.F0 = F0
        self.F1 = F1
        self.F2 = F2
        self.F3 = F3

    def printState(state): #print current state
        print(state.num,',',state.x,',',state.y,',',state.F0,',',state.F1,',',state.F2,',',state.F3)

def findValidNS(states,actions): #find valid next state
    validNS = {} #create dictionary to store validNS (Keys: state action pair, Val: next states)
    for state in states:
        for action in actions:
            sum = 0
            validNS[(state,action)] = []
            for ns in states:
                t = transition(state,action,ns)
                if t > 0:
                    sum += t
                    validNS[(state,action)].append(ns)
            if sum > 1.01 or sum < 0.99:
                print(state, action, sum)
    return validNS

def transition(state,action,nextState): #state transition function
    oldX = state.x
    oldY = state.y
    newX = nextState.x
    newY = nextState.y
    dMove = 1

    #check to see if next state is too far away to be possible
    if abs(newX - oldX) >= 1 and abs(newY - oldY) >= 1: #checks if diagonal movement
        return 0
    if abs(newX - oldX) > 1: #check for X boundary cross
        return 0
    if abs(newY - oldY) > 1: #check for y boundary cross
        return 0


    #check if next state locations line up with action taken
    if action == "Up":
        if newX != oldX: #x coordinate should not change
            return 0
        elif oldY == 0 and newY == 0: #y coord does not change if already at 0
            dMove = 1
        elif oldY > 0 and newY == oldY-1: #y coord should change otherwise
            dMove = 1
        else:
            return 0

    elif action == "Down":
        if newX != oldX: #if x coordinate changed
            return 0
        elif oldY == 2 and newY == 2: #y coord does not change if already at 2
            dMove = 1
        elif oldY < 2 and newY == oldY+1: #should not move up
            dMove = 1
        else:
            return 0

    elif action == "Left":
        if oldY != newY: #if y coordinate changed
            return 0
        elif oldX == 0 and newX == 0: #x coord does not change if already at 0
            dMove = 1
        elif oldX > 0 and newX == oldX-1: #X coord must change if not already 0
            dMove = 1
        else:
            return 0

    elif action == "Right":
        if oldY != newY: #if y coordinate changed
            return 0
        elif oldX == 2 and newX == 2: #x coord does not change if already at 2
            dMove = 1
        elif oldX < 2 and newX == oldX + 1: #x coord must change if not already 2
            dMove = 1
        else:
            return 0

    dF0 = 0
    dF1 = 0
    dF2 = 0
    dF3 = 0

    if action == "Extinguish":#how can everything change if it chooses extinguish
        if oldX != newX or oldY != newY:
            return 0
        if oldX == 0 and oldY == 0:
            dF0 = extinguishProb(state.F0,nextState.F0)
            dF1 = fireProb(state.F1,nextState.F1)
            dF2 = fireProb(state.F2,nextState.F2)
            dF3 = fireProb(state.F3,nextState.F3)
        elif oldX == 1 and oldY == 1:
            dF0 = fireProb(state.F0,nextState.F0)
            dF1 = extinguishProb(state.F1,nextState.F1)
            dF2 = fireProb(state.F2,nextState.F2)
            dF3 = fireProb(state.F3,nextState.F3)
        elif oldX == 2 and oldY == 0:
            dF0 = fireProb(state.F0,nextState.F0)
            dF1 = fireProb(state.F1,nextState.F1)
            dF2 = extinguishProb(state.F2,nextState.F2)
            dF3 = fireProb(state.F3,nextState.F3)
        elif oldX == 2 and oldY == 2:
            dF0 = fireProb(state.F0,nextState.F0)
            dF1 = fireProb(state.F1,nextState.F1)
            dF2 = fireProb(state.F2,nextState.F2)
            dF3 = extinguishProb(state.F3,nextState.F3)
        else:
            dF0 = fireProb(state.F0,nextState.F0)
            dF1 = fireProb(state.F1,nextState.F1)
            dF2 = fireProb(state.F2,nextState.F2)
            dF3 = fireProb(state.F3,nextState.F3)
    else:
        dF0 = fireProb(state.F0,nextState.F0)
        dF1 = fireProb(state.F1,nextState.F1)
        dF2 = fireProb(state.F2,nextState.F2)
        dF3 = fireProb(state.F3,nextState.F3)

    return dMove*dF0*dF1*dF2*dF3


def extinguishProb(oldVal, newVal): #returns the probability of a fire changing if it was extinguished
    if oldVal == 0:
        if newVal == 0:
            return 1
        else:
            return 0
    elif oldVal == 3:
        if newVal == 3:
            return 1
        else:
            return 0
    else:
        if oldVal == newVal:
            return 0.2
        elif oldVal-1 == newVal:
            return 0.8
        else:
            return 0

def fireProb(oldVal, newVal): #returns probability of fire changing in nothing was done to it
    if oldVal == 0:
        if newVal == 0:
            return 0.95
        elif newVal == 1:
            return 0.05
        else:
            return 0
    elif oldVal == 3:
        if newVal == 3:
            return 1
        else:
            return 0
    else:
        if oldVal == newVal:
            return 0.9
        elif oldVal+1 == newVal:
            return 0.1
        else:
            return 0

def reward(state,action): #determine reward for taking action a in state s
    NoFire = 0 #no of squares in state with intensity 0
    BurnedOut = 0 #no of squares in state with intensity 3
    E = 0 #reward dependent on action
    if action == "Up" or action == "Down" or action == "Left" or action ==  "Right": #if moving
        E = 0
    elif action == "Extinguish": #if extinguish chosen
        if state.x == 0 and state.y == 0: #if standing on F0
            if state.F0 == 1 or state.F0 == 2: #if fire is active
                E = 5
            else:
                E = -10
        elif state.x == 1 and state.y == 1: #if standing on F1
            if state.F1 == 1 or state.F1 == 2: #if fire is active
                E = 5
            else:
                E = -10
        elif state.x == 2 and state.y == 0: #if standing on F2
            if state.F2 == 1 or state.F2 == 2: #if fire is active
                E = 5
            else:
                E = -10
        elif state.x == 2 and state.y == 2: #if standing on F3
            if state.F3 == 1 or state.F3 == 2: #if fire is active
                E = 5
            else:
                E = -10
        else: #otherwise penalize
            E = -10
    for i in [state.F0,state.F1,state.F2,state.F3]: #loop through intensities
        if i == 0: #if fire is nonexistent, increment NoFire counter
            NoFire += 1
        elif i == 3: #if fire is burned out, increment burnout counter
            BurnedOut += 1
    reward = 10 * NoFire - 10 * BurnedOut + E #reward equation
    return reward

def valueIteration(validNS,states,actions,gamma,epsilon): #perform value iteration to return Q
    Q = [[0 for a in range(5)] for s in range(2304)] #init q table
    V = [0 for s in range(2304)] #init v table

    bigDiff = epsilon + 1 #initialize variable to check to see if our differences out within epsilon
    while bigDiff > epsilon:
        for state in states:
            for action in actions:
                R = reward(state,action)
                sum = 0
                for ns in validNS[(state,action)]:
                    sum += transition(state,action,ns)*V[ns.num]
                Q[state.num][actions.index(action)] = R + gamma*sum #calculate new Q table val using bellman equation
        newDiff = -math.inf
        for i in range(len(V)):
            newV = -math.inf
            oldV = V[i]
            for j in range(5): #loop through Q values to find new V value
                if Q[i][j] > newV:
                    newV = Q[i][j]
            V[i] = newV #update V table
            vDiff = newV - oldV
            if vDiff > newDiff:
                newDiff = vDiff 
        bigDiff = newDiff #update our largest difference 
    return Q



def main():
    gammaValue = sys.argv[1] #gamma argument
    epsilonValue = sys.argv[2] #epsilon argument
    stateFile = open("states.csv", "r") #read in csv of all states
    states = [] #create empty list to store states
    stateFile.readline() #skip over first line in file

    for line in stateFile: #populate set of states
        sVals = line.split(',')
        newState = State(int(sVals[0]),int(sVals[1]),int(sVals[2]),int(sVals[3]),int(sVals[4]),int(sVals[5]),int(sVals[6])) #populate state object with values from line
        states.append(newState)

    actions = ["Extinguish","Up","Down","Left","Right"] #construct set of possible calculations
    validNS = findValidNS(states,actions) #find a list of valid next states
    Q = valueIteration(validNS,states,actions,float(gammaValue),float(epsilonValue)) #perform value iteration
    policy = open("policy.txt","w") #create a policy file
    for i in range(2304):
        pol = 0
        for j in range(5):
            if Q[i][j] > Q[i][pol]:
                pol = j
        policy.write(str(i)+','+str(pol)+'\n') #populate policy file

main()
