package physics

import main.Player
import objects._
import scala.collection.mutable
import physics._

class World(var gravity: Double, var objects: List[PhysicalObject], var boundaries: List[Boundary], var players: List[Player],
            var projectiles: List[Projectile]) {

//  var players: mutable.MutableList[Player] = mutable.MutableList()

  val boundarievsSet: List[Boundary] = List.empty
  var lastUpdateTime: Double = System.nanoTime()

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

  def playersTakeDamage(): Unit = {
    for (player <- this.players) {

    }
  }

  def playerMoves(player: Player): Unit = {

  }

  def createPlayerBoundaries(): List[Boundary] = {
    var boundaryList: List[Boundary] = List.empty
    for (player <- this.players) {
      boundaryList :+ player.playerBoundary
    }
    boundaryList
  }

//Luke - I'm trying to work on updating each of the "objects" (meaning players and projectiles) with new locations and hit detection
// this is not finished by any means

  def update(time: Double): Unit = {
    var deltaTime: Double = time - lastUpdateTime
    var playerBoundaries: List[Boundary] = createPlayerBoundaries()
    for (player <- this.players) {
      val newPotentialLocation = player.computePotentialLocation(deltaTime)
      player.updateVelocity(this, deltaTime)
      var collision = player.detectCollision(newPotentialLocation, this.boundariesSet)
      if (collision == true) {
        player.location = newPotentialLocation
        player.bottom = new PhysicsVector(player.location.x, player.location.y - 50)
        player.top = new PhysicsVector(player.location.x, player.location.y + 50)
      }
    }
    for (projectile <- this.projectiles) {
      val newProjectileLocation = projectile.computePotentialLocation(deltaTime)

//      This will give the projectile bullet-drop
      projectile.updateVelocity(this, deltaTime)
      val projectileWallCollision = projectile.detectCollision(newProjectileLocation, this.boundariesSet)
      val projectilePlayerCollision = projectile.detectCollision(newProjectileLocation, playerBoundaries)
    }
  }
}