using UnityEngine;

public class PlayerMovement : MonoBehaviour
{
    public float speed = 6.0f;

    private Vector3 movement;
    private Animator anim;
    private Rigidbody rb;
    private int floorMask;
    private float camRayLength = 100f;

    // Like the Start() function but gets called even if the script isn't enabled
    void Awake()
    {
        floorMask = LayerMask.GetMask("Floor");
        anim = GetComponent<Animator>();
        rb = GetComponent<Rigidbody>();
    }

    void FixedUpdate()
    {
        // The raw values are either -1, 0 or 1 depending on which key was pressed
        // I.e., when pressing for example D, we immediately get the value 1, instead 
        // of a value slowly increasing to 1
        float h = Input.GetAxisRaw("Horizontal");
        float v = Input.GetAxisRaw("Vertical");

        Move(h, v);
        Turn();
        Animate(h, v);
    }

    void Move(float h, float v)
    {
        movement.Set(h, 0.0f, v);
        movement = movement.normalized * speed * Time.deltaTime;

        rb.MovePosition(transform.position + movement);
    }

    void Turn()
    {
        // Creates a ray from the camera, through the screen to the mouse position
        Ray camRay = Camera.main.ScreenPointToRay(Input.mousePosition);
        RaycastHit floorHit;

        // Checks if the ray hit the Floor layer and stores the result in floorHit
        if (Physics.Raycast(camRay, out floorHit, camRayLength, floorMask))
        {
            // Calculates the vector from the player's position to the point where the ray hit the floor
            Vector3 playerToMouse = floorHit.point - transform.position;
            playerToMouse.y = 0.0f; // Makes sure the y-component is zero

            // Uses the playerToMouse vector to turn the player towards the mouse
            Quaternion newRotation = Quaternion.LookRotation(playerToMouse);
            rb.MoveRotation(newRotation);
        }
    }

    void Animate(float h, float v)
    {
        bool walking = h != 0.0f || v != 0.0f;
        anim.SetBool("IsWalking", walking);
    }
}
