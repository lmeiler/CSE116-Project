package physics

import main.Player
import objects._

import scala.collection.mutable
import physics._
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

class World(var boundariesSet: List[Boundary]) {
  val EPSILON: Double = 1e-3

  //  var players: mutable.MutableList[Player] = mutable.MutableList()

  var projectiles: mutable.Buffer[Projectile] = mutable.Buffer.empty
  val gravity: Double = -240
  //  val boundariesSet: List[Boundary] = List.empty
  var lastUpdateTime: Double = System.nanoTime()
  var players: mutable.ListBuffer[Player] = mutable.ListBuffer[Player]()
 // var player: Player = new Player(new PhysicsVector(1, 0), new PhysicsVector(0, 0), "user", this)

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
  def shooting(): Unit = {
  this.projectiles+=new Projectile(new PhysicsVector(0,0), new PhysicsVector(0.0, 0.0))
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
    players += new Player(new PhysicsVector(1, 1), new PhysicsVector(0, 0),userName,this)
  }

  //  def playersTakeDamage(): Unit = {
  //    for (player <- this.players) {
  //
  //    }
  //  }

  //  def playerMoves(player: Player): Unit = {
  //
  //  }

  def createPlayerBoundaries(): Map[Player, Boundary] = {
    var boundaryMap: Map[Player, Boundary] = Map()
    for (player <- this.players) {
      boundaryMap = boundaryMap.updated(player, player.playerBoundary)
    }
    boundaryMap
  }

  //Luke - I'm trying to work on updating each of the "objects" (meaning players and projectiles) with new locations and hit detection
  // this is not finished by any means
  def updateVelocity(obj: PhysicalObject, world: World, dt: Double): Unit = {
    val newVelocity: PhysicsVector = new PhysicsVector(obj.velocity.x, obj.velocity.y - world.gravity *dt)
    obj.velocity.y = newVelocity.y
  }
  def computePotentialLocation(obj: PhysicalObject, dt: Double): PhysicsVector = {
    val xTraveled = obj.location.x + (obj.velocity.x * dt)
    val yTraveled = obj.location.y + (obj.velocity.y * dt)
    val newVector: PhysicsVector = new PhysicsVector( xTraveled ,yTraveled)
    newVector
  }



  def update(): Unit = {
    val time: Long = System.nanoTime()
    val deltaTime = (time - this.lastUpdateTime) / 1000000000.0
    var playerBoundaries: Map[Player, Boundary] = createPlayerBoundaries()
//    println(this.players+"backend")
    println(this.players)
    for (player <- this.players) {
//      println(player.velocity.x+"1")
//      println(player.velocity.y+"2")
      updateVelocity(player, this, deltaTime)

//            player.update(deltaTime)
      val newPotentialLocation =  computePotentialLocation(player, deltaTime)

      //      println(deltaTime)
//
//      for (platform <- this.boundariesSet) {
//        var collision = player.detectCollision(player, newPotentialLocation, platform)
//        if (collision) {
//          player.updateVelocity(this, deltaTime)
//          //        player.velocity.y -= this.gravity*deltaTime
//          player.location = newPotentialLocation
//          player.bottom = new PhysicsVector(player.location.x, player.location.y)
//          player.top = new PhysicsVector(player.location.x, player.location.y + 100)
//        }
//        else {
//          player.velocity.y = 0
//
//
//        }
//      }

      player.location = newPotentialLocation
      player.move(newPotentialLocation)
      println(player.velocity.x+"2")
      println(player.velocity.y+"2")
    }
    this.lastUpdateTime = time

    for (projectile <- this.projectiles) {
      if (this.projectiles.nonEmpty) {
        val newPotentialLocation = new PhysicsVector(projectile.location.x + projectile.velocity.x * deltaTime, projectile.location.y)
        projectile.location = newPotentialLocation

        for (player <- playerBoundaries.keys) {
          if (projectile.projectilePlayerCollision(projectile, deltaTime, player.playerBoundary)) {
            player.takeDamage
            this.projectiles -= projectile
          }
        }
      }
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
    this.lastUpdateTime = time

  }

  def gameState():String ={
    val gameState: Map[String, JsValue]= Map(
      "projectiles" -> Json.toJson(this.projectiles.map({ po => Json.toJson(Map("x" -> po.location.x, "y" -> po.location.y)) })),
      "players" -> Json.toJson( this.players.map( { player =>
        Json.toJson(Map(
          "x" -> Json.toJson(player.location.x),
          "y" -> Json.toJson(player.location.y),
          "v_x" -> Json.toJson(player.velocity.x),
          "v_y" -> Json.toJson(player.velocity.y),
          "username" -> Json.toJson(player.username),
          "health"-> Json.toJson(player.health)
        )
        )
      })
      )
    )
    Json.stringify(Json.toJson(gameState))
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