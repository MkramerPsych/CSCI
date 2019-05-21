import critter
import color
import random

class mkramer5(critter.Critter):
	lastAction = critter.ROAR
	lastResult = False

	def interact(self, oppInfo):
		if self.lastAction == critter.POUNCE or self.lastAction == critter.SCRATCH or self.lastAction == critter.ROAR:
			if oppInfo.getNeighbor(critter.CENTER) == "Partier":
				return critter.POUNCE
			else:
				return critter.PARTY
		else:
			return critter.SCRATCH

	def getColor(self):
		return color.YELLOW

	def getMove(self, info):
		if info.y < info.height:
			return critter.SOUTH
		if info.x < info.width:
			return critter.EAST
		
	def getChar(self):
		return "\u2653"

	def interactionOver(self, won, oppFight):
		self.lastAction = oppFight #get last action that occurred
		self.lastResult = won #get result of last interaction
		
	def __str__(self):
	    return '%s' % (self.__class__.__qualname__)
