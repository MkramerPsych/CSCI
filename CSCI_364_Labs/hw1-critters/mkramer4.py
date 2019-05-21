import critter
import color
import random

class mkramer4(critter.Critter):
	lastAction = critter.ROAR
	lastResult = False

	def interact(self, oppInfo):
		if self.lastAction == critter.POUNCE or self.lastAction == critter.SCRATCH or self.lastAction == critter.ROAR:
			moves = [critter.PARTY,critter.HEAL]
			if oppInfo.char != "\u2650":
				return moves[0]
			else:
				return moves[1]
		else:
			return critter.POUNCE

	def getColor(self):
		return color.GREEN

	def getMove(self, info):
		return critter.SOUTH
		
	def getChar(self):
		return "\u2650"

	def interactionOver(self, won, oppFight):
		self.lastAction = oppFight #get last action that occurred
		self.lastResult = won #get result of last interaction
		
	def __str__(self):
	    return '%s' % (self.__class__.__qualname__)
