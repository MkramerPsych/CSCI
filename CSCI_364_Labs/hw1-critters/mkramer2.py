import critter
import color
import random

class mkramer2(critter.Critter):
	lastAction = critter.ROAR
	lastResult = False
	lastOpType = ""

	def interact(self, oppInfo):
		if oppInfo.char == "\u2620":
			if oppInfo.getNeighborHealth(critter.CENTER) < 100:
				return critter.HEAL
			else:
				return critter.PARTY
				
		oppHealthN = oppInfo.getNeighborHealth(critter.NORTH)
		oppHealthS = oppInfo.getNeighborHealth(critter.SOUTH)
		oppHealthE = oppInfo.getNeighborHealth(critter.EAST)
		oppHealthW = oppInfo.getNeighborHealth(critter.WEST)
		healthList = [oppHealthN,oppHealthE,oppHealthS,oppHealthW]
				
		for health in healthList:
			if health < 50 and self.lastAction == critter.ROAR:
				return critter.POUNCE
			if health < 50 and self.lastAction == critter.POUNCE:
			    return critter.SCRATCH
			if health < 50 and self.lastAction == critter.SCRATCH:
			    return critter.ROAR

		else:
		    return critter.PARTY
		
	def getColor(self):
		return color.RED

	def getMove(self, info):
		if info.x < info.width / 2:
			return critter.EAST
		elif info.x > info.width / 2:
			return critter.WEST
		elif info.y < info.height / 2:
			return critter.SOUTH
		elif info.y > info.height / 2:
			return critter.NORTH
		else:
			return critter.CENTER
		
	def getChar(self):
		return "\u2620"

	def interactionOver(self, won, oppFight):
		self.lastAction = oppFight #get last action that occurred
		#get creature type of last interaction
		self.lastResult = won #get result of last interaction
		#get health of last interaction

	def __str__(self):
		return '%s' % (self.__class__.__qualname__)
