package tests
import main._
import objects.{Projectile, Shoot}
import physics._
import org.scalatest._

class TestUpdate extends FunSuite {
  test("Test update"){
    val boundary1 = new Boundary(new PhysicsVector(20, 150), new PhysicsVector(40, 150))
    val boundary2 = new Boundary(new PhysicsVector(40, 200), new PhysicsVector(60, 200))
    var boundaries: List[Boundary] = List(boundary1, boundary2)
    var player1: Player = new Player(new PhysicsVector(10, 0), new PhysicsVector(0, 0), "Player1")
    var player2: Player = new Player(new PhysicsVector(70, 0), new PhysicsVector(0, 0), "Player2")
    val players: List[Player] = List(player1, player2)
    val projectiles: List[Projectile] = List(player1.projectile, player2.projectile)
    val world = new World(10, boundaries, players, projectiles )

    // Jiaqi: For the update method, because I cannot know the time of the system exactly, so I set a delta time for
    // the test and I change it in the update method.

    // player1 move to right
    player1.player_movement(new PhysicsVector(1,0))
    // player2 move to left
    player2.player_movement(new PhysicsVector(-1,0))
    // Question: where is the collision is false situation?
    // player1 shoot
    player1.shooting()

    world.update()

    val player1_newLocation: PhysicsVector = new PhysicsVector(15, 0)
    val player2_newLocation: PhysicsVector = new PhysicsVector(65, 0)
    // val player1_projectile: Projectile = new Projectile(new PhysicsVector())
    assert(player1.location.x == player1_newLocation.x)
    assert(player2.location.x == player2_newLocation.x)




  }


}
