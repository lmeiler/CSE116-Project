package physics

import java.lang.Math._

import main.Player

object Physics {

  def computePotentialLocation(object1: PhysicalObject, deltaTime: Double): PhysicsVector = {
    var velocityVector: PhysicsVector = object1.velocity
    var locationVector: PhysicsVector = object1.location
    var velocityX: Double = velocityVector.x
    var velocityY: Double = velocityVector.y
    var velocityZ: Double = velocityVector.z
    var locationX: Double = locationVector.x
    var locationY: Double = locationVector.y
    var locationZ: Double = locationVector.z
    var xDistanceTraveled: Double = velocityX * deltaTime
    var yDistanceTraveled: Double = velocityY * deltaTime
    var zDistanceTraveled: Double = velocityZ * deltaTime
    var newXLocation: Double = locationX + xDistanceTraveled
    var newYLocation: Double = locationY + yDistanceTraveled
    var newZLocation: Double = (locationZ + zDistanceTraveled)
    if (newZLocation <= 0) {
      var groundVector: PhysicsVector = new PhysicsVector(newXLocation, newYLocation, 0.0)
      return groundVector
    }
    else {
      var newVector: PhysicsVector = new PhysicsVector(newXLocation, newYLocation, newZLocation)
      return newVector

    }
  }

  def updateVelocity(object2: PhysicalObject, world: World, deltaTime: Double): Unit = {
    var objectVelocity: PhysicsVector = object2.velocity
    var objectZVelocity: Double = objectVelocity.z
    var gravity: Double = world.gravity
    var deltaVelocity: Double = gravity*deltaTime
    var finalVelocity: Double = (objectZVelocity - deltaVelocity)
//    var halfATSquared: Double = (1/2)*(gravity)*(deltaTime)*(deltaTime)
    var vNotT: Double = objectZVelocity*deltaTime
//    var newZ: Double = vNotT - halfATSquared
    var newZ: Double = object2.location.z + vNotT
    var newVelocity: PhysicsVector = new PhysicsVector(object2.velocity.x, object2.velocity.y, finalVelocity)
    var newNonZVelocity: PhysicsVector = new PhysicsVector(object2.velocity.x, object2.velocity.y, 0.0)
    if(object2.location.z == 0.0 && object2.velocity.z < 0.0) {
      object2.velocity = newNonZVelocity
    }
    else {
      object2.velocity = newVelocity
    }
  }

  def detectCollision(object3: PhysicalObject, location3: PhysicsVector, boundary3: Boundary): Boolean = {
    var boundEnd1: PhysicsVector = boundary3.end1
    var boundEnd2: PhysicsVector = boundary3.end2
    var boundEnd1X: Double = boundEnd1.x
    var boundEnd1Y: Double = boundEnd1.y
    var boundEnd2X: Double = boundEnd2.x
    var boundEnd2Y: Double = boundEnd2.y
    var objx: Double = object3.location.x
    var objy: Double = object3.location.y
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
        return false
      }
      else {
        return true
      }
    }
    else {
      return true
    }
  }

  def updateWorld(world: World, deltaTime: Double): Unit = {
    for (obj <- world.objects) {
      updateVelocity(obj, world, deltaTime)
      var collision: Boolean = false
      for (bound <- world.boundaries) {
        if (!detectCollision(obj, computePotentialLocation(obj, deltaTime), bound)) {
          collision = true
        }
      }
      if (collision == true) {
        obj.location.z = computePotentialLocation(obj, deltaTime).z
      }
      else {
        obj.location = computePotentialLocation(obj, deltaTime)
      }
    }
  }

//  def main(args: Array[String]): Unit = {
//    var locationVector1: PhysicsVector = new PhysicsVector(5.0, 5.0, 0.0)
//    var velocityVector1: PhysicsVector = new PhysicsVector(1.0, 2.0, -3.0)
//    var object1: PhysicalObject = new PhysicalObject(locationVector1, velocityVector1)
//    var world: World = new World(9.8)
//    (updateVelocity(object1, world, 1.0))
//    println(object1.velocity.z)
//    var computeLocation : PhysicsVector = computePotentialLocation(object1, 1.0)
//    println(computeLocation)
//    var locationVector2: PhysicsVector = new PhysicsVector(-2.0, -2.0, 0.0)
//    var velocityVector2: PhysicsVector = new PhysicsVector(0.0, 0.0, 0.0)
//    var object2: PhysicalObject = new PhysicalObject(locationVector2, velocityVector2)
//    var boundaryVector1: PhysicsVector = new PhysicsVector(-20.0, 0.0, 0.0)
//    var boundaryVector2: PhysicsVector = new PhysicsVector(-10.0, 0.0, 0.0)
//    var boundary2: Boundary = new Boundary(boundaryVector1, boundaryVector2)
//    var finalLocation2: PhysicsVector = new PhysicsVector(5.0, 5.0, 0.0)
//    var world: World = new World(10.0, List(object2), List(boundary2))
//    println(detectCollision(object2, finalLocation2, boundary2))
  }
}