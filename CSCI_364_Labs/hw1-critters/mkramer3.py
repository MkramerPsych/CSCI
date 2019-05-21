import critter
import color
import random

class mkramer3(critter.Critter):
	lastAction = critter.ROAR
	lastResult = False

	def interact(self, oppInfo):
		if oppInfo.char == "\u2622": #heal/party with other saboteurs
			if oppInfo.getNeighborHealth(critter.CENTER) < 100:
				return critter.HEAL
			else:
				return critter.PARTY
		
		oppN = oppInfo.getNeighbor(critter.NORTH)
		oppE = oppInfo.getNeighbor(critter.EAST)
		oppS = oppInfo.getNeighbor(critter.SOUTH)
		oppW = oppInfo.getNeighbor(critter.WEST)

		oppList = [oppN,oppE,oppS,oppW]

		for opp in oppList:
			if self.lastAction == critter.ROAR or self.lastAction == critter.POUNCE or self.lastAction == critter.SCRATCH: #party if fought
				return critter.PARTY
			if self.lastAction == critter.HEAL or self.lastAction == critter.PARTY: #attack if healed or partied
				acts = [critter.ROAR,critter.POUNCE,critter.SCRATCH]
				rand1= random.randint(0, len(acts)-1)
				return acts[rand1]

	def getColor(self):
		return color.PURPLE

	def getMove(self, info):
		if info.x < info.width / 2:
			return critter.WEST
		elif info.x > info.width / 2:
			return critter.EAST
		elif info.y < info.height / 2:
			return critter.NORTH
		elif info.y > info.height / 2:
			return critter.SOUTH
		else:
			return critter.CENTER
		
	def getChar(self):
		return "\u2622"

	def interactionOver(self, won, oppFight):
		self.lastAction = oppFight #get last action that occurred
		#get creature type of last interaction
		self.lastResult = won #get result of last interaction
		#get health of last interaction

	def __str__(self):
		return '%s' % (self.__class__.__qualname__)
