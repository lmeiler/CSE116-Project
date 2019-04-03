package tests

import main.Player
import org.scalatest._
import physics.{Boundary, PhysicsVector, World}


class TestDetectCollision extends FunSuite {

  test("Tests it again") {
    var world: World = new World(List())
    val boundary1: Boundary = new Boundary(new PhysicsVector(-20,1), new PhysicsVector(-10,1))

    world.boundariesSet = List(boundary1)

    val player1: Player = new Player(new PhysicsVector(1, 2), new PhysicsVector(0,0), "user1", world)
    val location1: PhysicsVector = new PhysicsVector(5, 5)

    assert(player1.detectPlatform(location1, world.boundariesSet))
  }

  test("Tests the actual player situation") {
    var world: World = new World(List())
    val boundary1: Boundary = new Boundary(new PhysicsVector(10, 200), new PhysicsVector(310, 200))

    world.boundariesSet = List(boundary1)

    val player: Player = new Player(new PhysicsVector(200,100), new PhysicsVector(0,0), "user", world)
    val location: PhysicsVector = new PhysicsVector(200, 300)

    assert(!player.detectPlatform(location, world.boundariesSet))
  }

  test("Tests the hit detection") {
    var world: World = new World(List())
    val boundary1: Boundary = new Boundary(new PhysicsVector(-20,0), new PhysicsVector(20,0))

    world.boundariesSet = List(boundary1)

    val player1: Player = new Player(new PhysicsVector(-2, -2), new PhysicsVector(0,0), "user1", world)
    val location1: PhysicsVector = new PhysicsVector(5, 5)

    assert(!player1.detectPlatform(location1, world.boundariesSet))
  }

  test("Tests the initial conditional in DC") {
    var world: World = new World(List())
    val boundary1: Boundary = new Boundary(new PhysicsVector(10, 200), new PhysicsVector(310, 200))

    world.boundariesSet = List(boundary1)

    val player: Player = new Player(new PhysicsVector(200, 300), new PhysicsVector(0,0), "user", world)
    val location: PhysicsVector = new PhysicsVector(200, 300)

    assert(player.detectPlatform(location, world.boundariesSet) == false)
  }


}
