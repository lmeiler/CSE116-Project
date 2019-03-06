package physics

import main.Player
import objects._
import scala.collection.mutable

class World(var gravity: Double, var objects: List[PhysicalObject], var boundaries: List[Boundary], var players: List[Player],
            var projectiles: List[Projectile]) {

//  var players: mutable.MutableList[Player] = mutable.MutableList()


  def eliminatePlayers(): Unit = {
//    val buffer: mutable.Buffer[Player] = this.players.toBuffer
    val list1: List[Player] = this.players
    var index: Int = 0
    var buffer: mutable.Buffer[Player] = mutable.Buffer()
    for (player <- list1) {
      if (player.health != 0) {
//        buffer.remove(index)
        buffer += player
      }
      index += 1
    }
    val newList: List[Player] = buffer.toList
    this.players = newList
  }

  def checkWinner(): String = {
    if (this.players.size == 1) {
      val onlyPlayer: Player = this.players.last
      val playerName: String = onlyPlayer.username
      playerName + " has won!!"
    }
    else if(this.players.isEmpty) {
      "Draw"
    }
    else {
      "No Winner Yet"
    }
  }
}