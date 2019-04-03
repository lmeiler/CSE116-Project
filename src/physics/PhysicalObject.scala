package physics

import java.lang.Math.{max, min}

import com.sun.org.glassfish.external.statistics.BoundaryStatistic

import scala.collection.mutable.ListBuffer

abstract class PhysicalObject(var location: PhysicsVector, var velocity: PhysicsVector) {

  val epsilon: Double = .0000001

  def computePotentialLocation(deltaTime: Double): PhysicsVector = {
    val xTraveled = this.location.x + this.velocity.x*deltaTime
    val yTraveled = this.location.y + this.velocity.y*deltaTime
    val newVector: PhysicsVector = new PhysicsVector(this.location.x + xTraveled, this.location.y + yTraveled)
    return newVector
  }

  def updateVelocity(world: World, deltaTime: Double): Unit = {
    val newVelocity: PhysicsVector = new PhysicsVector(this.velocity.x, this.velocity.y - world.gravity*deltaTime)
    this.velocity.y = newVelocity.y
  }

  def findSlope(point1: PhysicsVector, point2: PhysicsVector): Double = {
    if (point2.x - point2.x == 0) {
      Double.PositiveInfinity
    }
    else {
      (point2.y - point1.y)/(point2.x - point1.x)
    }
  }

  def findYIntercept(point: PhysicsVector, slope: Double): Double = {
    point.y - (slope*point.x)
  }

  def areEquivalent(val1: Double, val2: Double): Boolean = {
    (val1 - val2).abs < epsilon
  }

  def detectPlatform(location1: PhysicsVector, boundaries: List[Boundary]): Boolean = {
    var booleanList: ListBuffer[Boolean] = ListBuffer(true)
    if (this.location.x == location1.x && this.location.y == location1.y) {
      booleanList += false
    }
    for (boundary <- boundaries) {
      val objectSlope: Double = findSlope(this.location, location1)
      val objectY = findYIntercept(this.location, objectSlope)

      val boundarySlope: Double = findSlope(boundary.end1, boundary.end2)
      val boundaryY: Double = findYIntercept(boundary.end1, boundarySlope)

      if (areEquivalent(objectSlope, boundarySlope)) {
        booleanList += false
      }

      val basicallyVertical = 10000
      if (Math.abs(objectSlope) > basicallyVertical) {
        val y = location1.x*boundarySlope + boundaryY
        val objectTop = max(this.location.y, location1.y)
        val objectBottom = max(this.location.y, location1.y)

        val boundaryLeft = min(boundary.end1.x, boundary.end2.x)
        val boundaryRight = max(boundary.end1.x, boundary.end2.x)

        val bool1: Boolean = (y >= objectBottom && y<= objectTop) && (this.location.x >= boundaryLeft && this.location.x <= boundaryRight)
        booleanList += bool1
      }

      else {
        val newX: Double = (boundaryY - objectY)/(objectSlope - boundarySlope)
        val newY: Double = newX*objectSlope + objectY

        val objectLeft = min(this.location.x, location1.x)
        val objectRight = max(this.location.x, location1.x)

        val boundaryLeft = min(boundary.end1.x, boundary.end2.x)
        val boundaryRight = max(boundary.end1.x, boundary.end2.x)

        val bool2: Boolean = (newX >= objectLeft - epsilon && newX <= objectRight + epsilon) && (newX >= boundaryLeft - epsilon && newX <= boundaryRight + epsilon)
        booleanList += bool2
      }
    }
    if (booleanList.contains(false)) {
      false
    }
    else {
      true
    }
  }

  def detectCollision(location3: PhysicsVector, boundaries: List[Boundary]): Boolean = {
    var booleanList: ListBuffer[Boolean] = ListBuffer(true)
    if (this.location.x == location3.x && this.location.y == location3.y) {
      booleanList += false
    }
    for (boundary <- boundaries) {
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
          booleanList += false
        }
        else {
        }
      }
      else {
      }
    }
    if (booleanList.contains(false)) {
      return false
    }
    else {
      return true
    }
  }
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
}
