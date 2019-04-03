package tests

import main.Player
import org.scalatest._
import physics.{Boundary, PhysicsVector, World}


class TestDetectCollision extends FunSuite {

  test("Tests the hit detection") {
    var world: World = new World(List())
    val boundary1: Boundary = new Boundary(new PhysicsVector(-20,0), new PhysicsVector(20,0))

    world.boundariesSet = List(boundary1)

    val player1: Player = new Player(new PhysicsVector(-2, -2), new PhysicsVector(0,0), "user1", world)
    val location1: PhysicsVector = new PhysicsVector(5, 5)

    assert(!player1.detectCollision(location1, world.boundariesSet))
  }

  test("Tests it again") {
    var world: World = new World(List())
    val boundary1: Boundary = new Boundary(new PhysicsVector(-20,0), new PhysicsVector(-10,0))

    world.boundariesSet = List(boundary1)

    val player1: Player = new Player(new PhysicsVector(-2, -2), new PhysicsVector(0,0), "user1", world)
    val location1: PhysicsVector = new PhysicsVector(5, 5)

    assert(player1.detectCollision(location1, world.boundariesSet))
  }
}
