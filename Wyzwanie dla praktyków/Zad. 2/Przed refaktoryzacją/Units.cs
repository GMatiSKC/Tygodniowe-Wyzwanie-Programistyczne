using UnityEngine;

using RTS;
public class Units : WorldObjects {
    public int attack, attackRange, walkSpeed;
    public Enums.UnitState state;

    protected bool isMoving = false, isRotating = false, isMining = false, isFighting = false;
    protected WorldObjects target;

    private Vector3 destination;
    private Quaternion targetRotation;
    private float fireStart = 0f;
    private float fireCooldown = 2f;
    private SavedObjects savedObjectsFile;

    protected override void Awake()
    {
        base.Awake();
    }

    protected override void Start()
    {
        base.Start();
        savedObjectsFile = GameObject.FindGameObjectWithTag("Player").GetComponent<SavedObjects>();
        if (savedObjectsFile)
        {
            savedObjectsFile.AddObjectToList(this);
        }
        //state = REST;
    }

    protected override void Update()
    {    
        if (isMoving)
        {
            if(this.GetComponent<Animation>().GetClip("Walk") != null)
                this.GetComponent<Animation>().CrossFade("Walk");
            else
                this.GetComponent<Animation>().CrossFade("walk");
            MakeMove();
        }

        if (target)
        {
            if (target.objectName == "Gold")
            {
                Mining();
            } else
            {
                if (target == this)
                {
                    target = null;
                } else
                {
                    AttackEnemy();
                }     
            }
        }

    }

    public override void MouseClick(GameObject hitObject, Vector3 hitPoint, Player controller)
    {
        
        if (currentlySelected && hitObject && hitObject.name != "Ground")
        {
            WorldObjects worldObject = hitObject.transform.root.GetComponent<WorldObjects>();
            if (worldObject)
            {
                {
                    this.transform.LookAt(worldObject.transform);
                    target = worldObject;
                }
            }
        }
        else { 
            base.MouseClick(hitObject, hitPoint, controller);        
            if (currentlySelected && hitObject && hitObject.name == "Ground")
            {
                target = null;
                this.transform.LookAt(hitPoint);
                destination = hitPoint;
                isMoving = true;
            }
        }
    }

    private void StartMove(Vector3 _destination)
    {
        destination = _destination;
        targetRotation = Quaternion.LookRotation(destination - transform.position);
        isRotating = true;
        isMoving = false;
    }

    private void MakeMove()
    {
        transform.position = Vector3.MoveTowards(transform.position, destination, Time.deltaTime * walkSpeed);
        
        if (transform.position == destination)
        {
            if (this.GetComponent<Animation>().GetClip("Idle") != null)
                this.GetComponent<Animation>().CrossFade("Idle");
            else
                this.GetComponent<Animation>().CrossFade("idle");
            isMoving = false;
        }
    }

    private void TurnToTarget()
    {
        transform.rotation = Quaternion.RotateTowards(transform.rotation, targetRotation, 10);
        Quaternion inverseTargetRotation = new Quaternion(-targetRotation.x, -targetRotation.y, -targetRotation.z, -targetRotation.w);
        if (transform.rotation == targetRotation || transform.rotation == inverseTargetRotation)
        {
            isRotating = false;
            isMoving = true;
        }
    }

    protected virtual void AttackEnemy()
    {
        if (Vector3.Distance(this.transform.position, target.transform.position) > attackRange)
        {
            destination = target.transform.position;
            isMoving = true;
        } else
        {
            isMoving = false;
            if (Time.time > fireStart + fireCooldown)
            {
                if (this.GetComponent<Animation>().GetClip("Lumbering") != null)
                    this.GetComponent<Animation>().CrossFade("Lumbering");
                else
                    this.GetComponent<Animation>().CrossFade("attack");

                target.GetComponent<WorldObjects>().GetHit(10);

                if (this.GetComponent<Animation>().GetClip("Idle") != null)
                    this.GetComponent<Animation>().PlayQueued("Idle");
                else
                    this.GetComponent<Animation>().PlayQueued("idle");
                fireStart = Time.time;
            }
        }
    }

    protected void Mining()
    {
        if (Vector3.Distance(this.transform.position, target.transform.position) > attackRange)
        {
            destination = target.transform.position;
            isMoving = true;
        } else
        {
            isMoving = false;
            isMining = true;
            if (Time.time > fireStart + fireCooldown)
            {
                if (this.GetComponent<Animation>().GetClip("Lumbering") != null)
                {
                    this.GetComponent<Animation>().CrossFade("Lumbering");
                    GameObject.FindObjectOfType<Player>().IncreaseGold(10);
                    fireStart = Time.time;
                }
            }
        }
    }

    protected void OnMouseOver()
    {

    }

    void OnDestroy()
    {
        savedObjectsFile.DeleteObjectFromList(this);
    }
}
