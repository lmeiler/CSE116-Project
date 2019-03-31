package objects

import java.lang.Math.{max, min}

import physics.{Boundary, PhysicsVector, World}
import main._

class Projectile(location: PhysicsVector, velocity: PhysicsVector)
  extends InanimateObject(location, velocity) {

  var damage: Int = 2

  override def use(player: Player): Unit = {
    this.velocity.x = player.orientation.x*player.strength
    this.velocity.y = player.orientation.y*player.strength
    //this.velocity.z = player.orientation.z*player.strength
  }

  def detectPlayerCollision(location3: PhysicsVector, player: Player): List[Player] = {
    val boundary = player.playerBoundary
    var boundEnd1: PhysicsVector = boundary.end1
    var boundEnd2: PhysicsVector = boundary.end2
    var boundEnd1X: Double = boundEnd1.x
    var boundEnd1Y: Double = boundEnd1.y
    var boundEnd2X: Double = boundEnd2.x
    var boundEnd2Y: Double = boundEnd2.y
    var objx: Double = this.location.x
    var objy: Double = this.location.y
    var locx: Double = location3.x
    var locy: Double = location3.y
    var objectSlope: Double = (locy - objy)/(locx - objx)
    var boundSlope: Double = (boundEnd2Y - boundEnd1Y)/(boundEnd2X - boundEnd1X)
    var objYInt: Double = (objy - (objectSlope)*objx)
    var boundYInt: Double = (boundEnd1Y - (boundSlope)*boundEnd1X)
    var xIntercept: Double = (boundYInt - objYInt)/(objectSlope - boundSlope)
    var minX1: Double = min(objx, locx)
    var minX2: Double = min(boundEnd1X, boundEnd2X)
    var maxX1: Double = max(objx, locx)
    var maxX2: Double = max(boundEnd1X, boundEnd2X)
    if (minX1 <= xIntercept && xIntercept <= maxX1) {
      if (minX2 <= xIntercept && xIntercept <= maxX2) {
        return List(player)
      }
      else {
        return List.empty
      }
    }
    else {
      return List.empty
    }
  }

//  def projectilePlayerCollision(location: PhysicsVector, world: World, playerList: List[Player]): Player = {
//    var playerBoundaries: List[Boundary] = List.empty
//    for (player <- playerList) {
//      playerBoundaries :+ player.playerBoundary
//    }
//    this.detectCollision(location, playerBoundaries)
//  }
}
