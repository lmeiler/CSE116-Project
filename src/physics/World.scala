package physics

import main.Player
import objects._

import scala.collection.mutable
import physics._
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

class World(var boundariesSet: List[Boundary]) {

  //  var players: mutable.MutableList[Player] = mutable.MutableList()

  var projectiles: mutable.Buffer[Projectile] = mutable.Buffer.empty
  val gravity: Double = -240
  //  val boundariesSet: List[Boundary] = List.empty
  var lastUpdateTime: Double = System.nanoTime()
  var players: mutable.ListBuffer[Player] = mutable.ListBuffer[Player]()
  var player: Player = new Player(new PhysicsVector(1, 0), new PhysicsVector(0, 0), "user", this)

  def eliminatePlayers(): Unit = {
    //    val buffer: mutable.Buffer[Player] = this.players.toBuffer
    //    val list1: List[Player] = this.players
    var index: Int = 0
    //    var buffer: mutable.Buffer[Player] = mutable.Buffer()
    for (player <- this.players) {
      if (player.health == 0) {
        //        buffer.remove(index)
        this.players -= player
      }
      index += 1
    }
    //    val newList: List[Player] = buffer.toList
    //    this.players = newList
  }

  def checkWinner(): String = {
    if (this.players.size == 1) {
      val onlyPlayer: Player = this.players.last
      val playerName: String = onlyPlayer.username
      playerName + " has won!!"
    }
    else if (this.players.isEmpty) {
      "Draw"
    }
    else {
      "No Winner Yet"
    }
  }
  def addPlayer(userName:String): Unit ={
    players += new Player(new PhysicsVector(1, 0), new PhysicsVector(0, 0),userName,this)
  }

  //  def playersTakeDamage(): Unit = {
  //    for (player <- this.players) {
  //
  //    }
  //  }

  //  def playerMoves(player: Player): Unit = {
  //
  //  }

  def createPlayerBoundaries(): List[Boundary] = {
    var boundaryList: List[Boundary] = List.empty
    for (player <- this.players) {
      boundaryList :+ player.playerBoundary
    }
    boundaryList
  }

  //Luke - I'm trying to work on updating each of the "objects" (meaning players and projectiles) with new locations and hit detection
  // this is not finished by any means

  def update(deltaTime: Double): Unit = {
    var playerBoundaries: List[Boundary] = createPlayerBoundaries()
    for (player <- this.players) {
      //      player.update(deltaTime)
      val newPotentialLocation = new PhysicsVector(player.location.x + player.velocity.x * deltaTime, player.location.y + player.velocity.y * deltaTime)
      for (platform <- this.boundariesSet) {
        var collision = player.detectCollision(newPotentialLocation, platform)
        if (collision == true) {
          player.updateVelocity(this, deltaTime)
          //        player.velocity.y -= this.gravity*deltaTime
          player.location = newPotentialLocation
          player.bottom = new PhysicsVector(player.location.x, player.location.y)
          player.top = new PhysicsVector(player.location.x, player.location.y + 100)
        }
        else {
          player.velocity.y = 0
        }
      }
    }
    for (projectile <- this.projectiles) {
      val newPotentialLocation = new PhysicsVector(projectile.location.x + projectile.velocity.x * deltaTime, projectile.location.y)
      projectile.location = newPotentialLocation
    }

    //      val newProjectileLocation = projectile.computePotentialLocation(deltaTime)
    //
    //      //      This will give the projectile bullet-drop
    //      // projectile.updateVelocity(this, deltaTime)
    //
    //      val projectileWallCollision = projectile.detectCollision(newProjectileLocation, this.boundariesSet)
    //      if (projectileWallCollision == true) {
    //        projectile.location = newProjectileLocation
    //      }
    //      if (projectileWallCollision == false) {
    //        //        var projectileBuffer = this.projectiles.toBuffer
    //        this.projectiles - projectile
    //        //        this.projectiles = projectileBuffer.toList
    //      }
    //      for (player <- this.players) {
    //        val projectilePlayerCollision = projectile.detectPlayerCollision(newProjectileLocation, player)
    //        if (!projectilePlayerCollision.isEmpty) {
    //          projectilePlayerCollision.head.health - 2
    //        }
    //      }
    //    }
    eliminatePlayers()
  }
  def gameState():String ={
    val gameState: Map[String, JsValue]= Map(
      "projectiles" -> Json.toJson( Map(
        projectiles.map({ po=>
          Json.toJson(Map("x"-> po.location.x,"y"-> po.location))
        })
      )
      ),
      "players" -> Json.toJson( this.players.map( { case (player) =>
        Json.toJson(Map(
          "x" -> Json.toJson(player.location.x),
          "y" -> Json.toJson(player.location.y),
          "v_x" -> Json.toJson(player.location.x),
          "v_y" -> Json.toJson(player.location.y),
          "username" -> Json.toJson(player.username),
          "health"-> Json.toJson(player.health)
        )
        )
      })
      ),

    )
  }
}


//  def tempUpdate(deltaTime: Double): Unit = {
//    for (player <- this.players) {
//      val newPotentialLocation = player.computePotentialLocation(deltaTime)
//      player.location.x = player.location.x + (player.velocity.x*deltaTime)
//      player.location.y = player.location.y + (player.velocity.y*deltaTime)
//    }
//  }

  //This is a WIP for now, ignore if necessary

//  def singlePlayerUpdate(deltaTime: Double): Unit = {
//    var playerBoundaries: List[Boundary] = createPlayerBoundaries()
//    player.update(deltaTime)
//    val newPotentialLocation = player.computePotentialLocation(deltaTime)
//    var collision = player.detectCollision(newPotentialLocation, this.boundariesSet)
//    if (collision == true) {
//      player.updateVelocity(this, deltaTime)
//      player.location = newPotentialLocation
//      player.bottom = new PhysicsVector(player.location.x, player.location.y)
//      player.top = new PhysicsVector(player.location.x, player.location.y + 100)
//    }
//    else {
//      player.velocity.y = 0
//    }
//    for (projectile <- this.projectiles) {
//      val newProjectileLocation = projectile.computePotentialLocation(deltaTime)
//      //      This will give the projectile bullet-drop
////      projectile.updateVelocity(this, deltaTime)
//
//      val projectileWallCollision = projectile.detectCollision(newProjectileLocation, this.boundariesSet)
//      if (projectileWallCollision == true) {
//        projectile.location = newProjectileLocation
//      }
//      if (projectileWallCollision == false) {
//        //        var projectileBuffer = this.projectiles.toBuffer
//        this.projectiles - projectile
//        //        this.projectiles = projectileBuffer.toList
//      }
//
//      val projectilePlayerCollision = projectile.detectPlayerCollision(newProjectileLocation, player)
//      if (!projectilePlayerCollision.isEmpty) {
//        projectilePlayerCollision.head.health - 2
//      }
//    }
//  }
//  eliminatePlayers()
//}