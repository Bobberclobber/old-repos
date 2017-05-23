using UnityEngine;

public class PlayerShooting : MonoBehaviour
{
    public int damagePerShot = 20;
    public float timeBetweenBullets = 0.15f;
    public float range = 100f;


    private float timer;
    private Ray shootRay;
    private RaycastHit shootHit;
    private int shootableMask;
    private ParticleSystem gunParticles;
    private LineRenderer gunLine;
    private AudioSource gunAudio;
    private Light gunLight;
    private float effectsDisplayTime = 0.2f;


    void Awake ()
    {
        shootableMask = LayerMask.GetMask("Shootable");
        gunParticles = GetComponent<ParticleSystem> ();
        gunLine = GetComponent <LineRenderer> ();
        gunAudio = GetComponent<AudioSource> ();
        gunLight = GetComponent<Light> ();
    }


    void Update ()
    {
        timer += Time.deltaTime;

		if(Input.GetButton("Fire1") && timer >= timeBetweenBullets && Time.timeScale != 0)
            Shoot ();

        if(timer >= timeBetweenBullets * effectsDisplayTime)
            DisableEffects ();
    }


    public void DisableEffects ()
    {
        // Disabling components works like this
        // When disabling entire GameObjects, GameObject.SetActive(false) is used
        gunLine.enabled = false;
        gunLight.enabled = false;
    }


    void Shoot ()
    {
        timer = 0f;

        gunAudio.Play ();

        gunLight.enabled = true;

        gunParticles.Stop ();
        gunParticles.Play ();

        gunLine.enabled = true;
        // Set the first position of the line to be the current position of the GunBarrelEnd object
        gunLine.SetPosition (0, transform.position); 

        // Find out the end position of the line by shooting a ray and using the position it hits as the end position
        shootRay.origin = transform.position;
        shootRay.direction = transform.forward;

        // Physics.Raycast shoots the given ray (shootRay with the transform set above) at a given range, checking if it hit anything
        // which is part of the given layer (shootableMask) and stores the position in the RaycastHit marked 'out' (shootHit) 
        if(Physics.Raycast (shootRay, out shootHit, range, shootableMask))
        {
            EnemyHealth enemyHealth = shootHit.collider.GetComponent <EnemyHealth> ();
            if(enemyHealth != null)
                enemyHealth.TakeDamage (damagePerShot, shootHit.point);
            gunLine.SetPosition (1, shootHit.point);
        }
        else
            gunLine.SetPosition (1, shootRay.origin + shootRay.direction * range); // Just draw a long line if nothing was hit
    }
}
