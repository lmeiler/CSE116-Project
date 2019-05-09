package tests

import main._
import objects.{Projectile, Shoot}
import physics._
import org.scalatest._

class TestMovement extends FunSuite {
  test("Test left and right") {
    val boundary1 = new Boundary(new PhysicsVector(20, 150), new PhysicsVector(40, 150))
    val boundary2 = new Boundary(new PhysicsVector(40, 200), new PhysicsVector(60, 200))
    val ground = new Boundary(new PhysicsVector(0, 0), new PhysicsVector(400, 0))

    var boundaries: List[Boundary] = List(boundary1, boundary2, ground)
    val world = new World(boundaries)
    var player1: Player = new Player(new PhysicsVector(10, 0), new PhysicsVector(0, 0), "Player1", world)
    var player2: Player = new Player(new PhysicsVector(70, 0), new PhysicsVector(0, 0), "Player2", world)
    var player3: Player = new Player(new PhysicsVector(35, 0), new PhysicsVector(0, 0), "Player3", world)

    world.players += player1
    world.players += player2
    world.players += player3

    // Press left to player1
    player1.moveLeft()
    player2.moveRight()
    player3.jump()

/*
    val player1_newLocation: PhysicsVector = new PhysicsVector(310, 0)
    val player2_newLocation: PhysicsVector = new PhysicsVector(-230, 0)
    val player1_velocity: PhysicsVector = new PhysicsVector(300, 240)
    val player2_velocity: PhysicsVector = new PhysicsVector(-300, 240)
*/
    assert(player1.velocity.x == -10)
    assert(player2.velocity.x == 10)
  }


}