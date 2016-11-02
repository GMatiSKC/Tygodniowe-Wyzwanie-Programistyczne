using UnityEngine;
using RTS;

public class Units : WorldObjects {
    public int attack, attackRange, walkSpeed;

	protected UnitStates unitState;
    protected WorldObjects target;

    private float fireStart = 0f;
    private float fireCooldown = 2f;
    private Vector3 targetPosition;
    private Quaternion targetRotation;
    private SavedObjects savedObjectsFile;

    private static string PLAYER = "Player";
    private static string GROUND = "Ground";
    private static string GOLD = "Gold";

    protected override void Awake()
    {
        base.Awake();
    }

    protected override void Start()
    {
        base.Start();

        this.savedObjectsFile = GameObject.FindGameObjectWithTag(PLAYER).GetComponent<SavedObjects>();

        if (this.savedObjectsFile)
        {
            this.savedObjectsFile.AddObjectToList(this);
        }
    }

    protected override void Update()
    {    
        switch (this.unitState)
        {
            case UnitState.MOVE:
                MakeMove();
                break;
            case UnitState.MINING:
                Mining();
                break;
            case UnitState.FIGHT:
                AttackEnemy();
                break;
            case UnitState.REST:
                break;
        }
    }

    public override void MouseClick(GameObject hitObject, Vector3 hitPoint, Player controller)
    {     
        if (currentlySelected && hitObject && hitObject.name != GROUND)
        {
            WorldObjects worldObject = hitObject.transform.root.GetComponent<WorldObjects>();
            if (worldObject)
            {
                this.transform.LookAt(worldObject.transform);
                this.target = worldObject;

                if (this.target.objectName == GOLD) {
                    this.unitState.MINING;
                }
                else {
                    if (this.target == this)
                    {
                        this.target = null;
                        this.unitState.REST;
                    }
                    else
                    {
                        this.unitState.FIGHT;
                    }
                } 
            }
        }
        else { 
            base.MouseClick(hitObject, hitPoint, controller);        
            if (currentlySelected && hitObject && hitObject.name == GROUND)
            {
                this.target = null;
                this.transform.LookAt(hitPoint);
                this.targetPosition = hitPoint;
                this.unitState.MOVE;
            }
        }
    }

    private void MakeMove()
    {
        this.GetComponent<Animation>().CrossFade(UnitAnimations.WALK);
        this.transform.position = Vector3.MoveTowards(transform.position, targetPosition, Time.deltaTime * walkSpeed);
        
        if (transform.position == targetPosition)
        {
            this.GetComponent<Animation>().CrossFade(UnitAnimations.IDLE);
            this.unitState.REST;
        }
    }
    
    protected virtual void AttackEnemy()
    {
        if (Vector3.Distance(this.transform.position, target.transform.position) > this.attackRange)
        {
            this.targetPosition = target.transform.position;
            this.unitState.MOVE;
        } else
        {
            isMoving = false;
            if (Time.time > fireStart + fireCooldown)
            {
                this.GetComponent<Animation>().CrossFade(UnitAnimations.LUMBERING);
                target.GetComponent<WorldObjects>().GetHit(10);

                fireStart = Time.time;
            }
        }
    }

    protected void Mining()
    {
        if (Vector3.Distance(this.transform.position, target.transform.position) > attackRange)
        {
            targetPosition = target.transform.position;
            unitState.MOVE;
        } else
        {
            unitState.MINING;
            if (Time.time > fireStart + fireCooldown)
            {
                this.GetComponent<Animation>().CrossFade(UnitAnimations.LUMBERING);

                GameObject.FindObjectOfType<Player>().IncreaseGold(10);
                fireStart = Time.time;
            }
        }
    }
    
    void OnDestroy()
    {
        savedObjectsFile.DeleteObjectFromList(this);
    }
}
