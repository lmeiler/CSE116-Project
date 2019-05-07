package physics

import java.lang.Math.{max, min}

import objects.Projectile

import scala.collection.mutable.ListBuffer

abstract class PhysicalObject(var location: PhysicsVector, var velocity: PhysicsVector) {

  val epsilon: Double = .0000001

  def computePotentialLocation(deltaTime: Double): PhysicsVector = {
    val xTraveled = this.location.x + this.velocity.x * deltaTime
    val yTraveled = this.location.y + this.velocity.y * deltaTime
    val newVector: PhysicsVector = new PhysicsVector(this.location.x + xTraveled, this.location.y + yTraveled)
    newVector
  }

  def updateVelocity(world: World, deltaTime: Double): Unit = {
    val newVelocity: PhysicsVector = new PhysicsVector(this.velocity.x, this.velocity.y - world.gravity * deltaTime)
    this.velocity.y = newVelocity.y
  }


  def detectCollision(para: PhysicalObject, potential_pos: PhysicsVector, boundary: Boundary): Boolean = {

    if (Math.max(para.location.x, potential_pos.x) < Math.min(boundary.end1.x, boundary.end2.x)
      || Math.max(para.location.y, potential_pos.y) < Math.min(boundary.end1.y, boundary.end2.y)
      || Math.max(boundary.end1.x, boundary.end2.x) < Math.min(para.location.x, potential_pos.x)
      || Math.max(boundary.end1.y, boundary.end2.y) < Math.min(para.location.y, potential_pos.y)) {
      return true
    }
    if ((((para.location.x - boundary.end1.x) * (boundary.end2.y - boundary.end1.y) - (para.location.y - boundary.end1.y) * (boundary.end2.x - boundary.end1.x)) *
      ((potential_pos.x - boundary.end1.x) * (boundary.end2.y - boundary.end1.y) - (potential_pos.y - boundary.end1.y) * (boundary.end2.x - boundary.end1.x))) > 0 ||
      (((boundary.end1.x - para.location.x) * (potential_pos.y - para.location.y) - (boundary.end1.y - para.location.y) * (potential_pos.x - para.location.x)) *
        ((boundary.end2.x - para.location.x) * (potential_pos.y - para.location.y) - (boundary.end2.y - para.location.y) * (potential_pos.x - para.location.x))) > 0) {
      return true
    }
    false
  }

  def projectilePlayerCollision(projectile: Projectile, deltaTime: Double, boundary: Boundary): Boolean = {
    if (projectile.location.x > boundary.end2.x) {
      val newLoc = projectile.computePotentialLocation(deltaTime)
      if (newLoc.x < boundary.end2.x) {
        return true
      }
      else {
        return false
      }
    }

    if (projectile.location.x < boundary.end2.x) {
      val newLoc = projectile.computePotentialLocation(deltaTime)
      if (newLoc.x > boundary.end2.x) {
        return true
      }
      else {
        return false
      }
    }
    else {
      return false
    }
  }
}



  /*
  def detectCollision(location3: PhysicsVector, boundary: Boundary): Boolean = {
    if (this.location.x == location3.x && this.location.y == location3.y) {
      return false
    }
    var boundEnd1: PhysicsVector = boundary.end1
    var boundEnd2: PhysicsVector = boundary.end2
    var boundEnd1X: Double = boundEnd1.x
    var boundEnd1Y: Double = -boundEnd1.y
    var boundEnd2X: Double = boundEnd2.x
    var boundEnd2Y: Double = -boundEnd2.y
    var objx: Double = this.location.x
    var objy: Double = -this.location.y
    var locx: Double = location3.x
    var locy: Double = -location3.y
    var objectSlope: Double = (locy - objy) / (locx - objx)
    var boundSlope: Double = (boundEnd2Y - boundEnd1Y) / (boundEnd2X - boundEnd1X)
    var objYInt: Double = (objy - (objectSlope) * objx)
    var boundYInt: Double = (boundEnd1Y - (boundSlope) * boundEnd1X)
    var xIntercept: Double = (boundYInt - objYInt) / (objectSlope - boundSlope)
    var minX1: Double = min(objx, locx)
    var minX2: Double = min(boundEnd1X, boundEnd2X)
    var maxX1: Double = max(objx, locx)
    var maxX2: Double = max(boundEnd1X, boundEnd2X)
    if (minX1 <= xIntercept && xIntercept <= maxX1) {
      if (minX2 <= xIntercept && xIntercept <= maxX2) {
        return false
      }
      return true
    }
    return true
  }
}
*/
//    for (boundary <- boundaries) {
//      var boundEnd1: PhysicsVector = boundary.end1
//      var boundEnd2: PhysicsVector = boundary.end2
//      var boundEnd1X: Double = boundEnd1.x
//      var boundEnd1Y: Double = boundEnd1.y
//      var boundEnd2X: Double = boundEnd2.x
//      var boundEnd2Y: Double = boundEnd2.y
//      var objx: Double = this.location.x
//      var objy: Double = this.location.y
//      var locx: Double = location3.x
//      var locy: Double = location3.y
//      var objectSlope: Double = (locy - objy) / (locx - objx)
//      var boundSlope: Double = (boundEnd2Y - boundEnd1Y) / (boundEnd2X - boundEnd1X)
//      var objYInt: Double = (objy - (objectSlope) * objx)
//      var boundYInt: Double = (boundEnd1Y - (boundSlope) * boundEnd1X)
//      var xIntercept: Double = (boundYInt - objYInt) / (objectSlope - boundSlope)
//      var minX1: Double = min(objx, locx)
//      var minX2: Double = min(boundEnd1X, boundEnd2X)
//      var maxX1: Double = max(objx, locx)
//      var maxX2: Double = max(boundEnd1X, boundEnd2X)
//      if (minX1 <= xIntercept && xIntercept <= maxX1) {
//        if (minX2 <= xIntercept && xIntercept <= maxX2) {
//          return false
//        }
//      }
//    }
//    return true
//  }

//  def detectHorizontalCollision(location1: PhysicsVector, boundaries: List[Boundary]): Boolean = {
//    for (boundary <- boundaries) {
//      val objectSlope = (location1.y - this.location.y)/(location1.x - this.location.x)
//      val boundSlope = (boundary.end2.y - boundary.end1.y)/(boundary.end2.x - boundary.end1.x)
//
//    }
//  }

    //        }
//
//      }
//      else {
//        return true
//      }
//    }
//    else {
//      return true
//    }
//  }

//  def updateWorld(world: World, deltaTime: Double): Unit = {
//    for (obj <- world.objects) {
//      updateVelocity(obj, world, deltaTime)
//      var collision: Boolean = false
//      for (bound <- world.boundaries) {
//        if (!detectCollision(obj, computePotentialLocation(obj, deltaTime), bound)) {
//          collision = true
//        }
//      }
//      if (collision == true) {
//        obj.location.z = computePotentialLocation(obj, deltaTime).z
//      }
//      else {
//        obj.location = computePotentialLocation(obj, deltaTime)
//      }
//    }
//  }
