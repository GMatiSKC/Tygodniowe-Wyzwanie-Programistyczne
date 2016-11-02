using UnityEngine;
using System.Collections;

namespace RTS
{
    public class UnitStates
    {
        private readonly string name;

        public static readonly UnitStates REST = new UnitStates("Rest");
        public static readonly UnitStates MOVE = new UnitStates("Move");
        public static readonly UnitStates FIGHT = new UnitStates("Fight");
        public static readonly UnitStates MINING = new UnitStates("Mining");

        private UnitStates(string name)
        {
            this.name = name;
        }

        public override string ToString()
        {
            return name;
        }
    }

    public class UnitAnimations
    {
        private readonly string name;

        public static readonly UnitAnimations WALK = new UnitAnimations("Walk");
        public static readonly UnitAnimations IDLE = new UnitAnimations("Idle");
        public static readonly UnitAnimations LUMBERING = new UnitAnimations("Lumbering");
        public static readonly UnitAnimations ATTACK = new UnitAnimations("Attack");

        private UnitAnimations(string name)
        {
            this.name = name;
        }

        public override string ToString()
        {
            return name;
        }
    }
}
