import critter
import color
import random

class mkramer1(critter.Critter):
	lastAction = critter.ROAR
	lastResult = False

	def interact(self, oppInfo):
		if oppInfo.char == "T": #Always heal other Trumps
			if oppInfo.getNeighborHealth(critter.CENTER) < 100:
				return critter.HEAL
			else:
				return critter.PARTY
		
		elif self.lastAction == critter.ROAR:
			return critter.POUNCE
		elif self.lastAction == critter.POUNCE:
			return critter.SCRATCH
		elif self.lastAction == critter.SCRATCH:
			return critter.ROAR
		else:
			if self.lastResult == True:
				return critter.PARTY
			else:
			    return critter.HEAL

	def getColor(self):
		return color.ORANGE

	def getMove(self, info):
		if info.x == 0 or info.x == info.width /2:
			return critter.CENTER

		elif info.y == 0 or info.y == info.height: #if not on width border, move
			return critter.CENTER
	
		else:
			moves = [critter.NORTH, critter.SOUTH, critter.EAST, critter.WEST]
			rand = random.randint(0, len(moves)-1)
			return moves[rand]
		
	def getChar(self):
		return "\u2623"

	def interactionOver(self, won, oppFight):
		self.lastAction = oppFight #get last action that occurred
		#get creature type of last interaction
		self.lastResult = won #get result of last interaction
		#get health of last interaction

	def __str__(self):
		return '%s' % (self.__class__.__qualname__)
