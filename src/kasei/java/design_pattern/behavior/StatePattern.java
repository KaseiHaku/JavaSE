
public class StatePattern {

    private static class StateOwner {
        private StateAbstract state;

        final private static StateAbstract STATE_1 = new State1();
        final private static StateAbstract STATE_2 = new State2();


        public void setState(StateAbstract state) {
            this.state = state;
            state.setOwner(this);
        }

        public void executeByState(){
            if (state == null) {
                System.out.println("no state is declare");
                return ;
            }
            state.run();
        }
    }


    private abstract static class StateAbstract {
        protected StateOwner owner;

        public void setOwner(StateOwner owner) {
            this.owner = owner;
        }
        abstract void run();
    }
    private static class State1 extends StateAbstract {
        @Override
        public void run() {
            if (owner == null) {
                System.out.println("state owner is null");
                return ;
            }
            System.out.println("current state is one");
            this.owner.setState(StateOwner.STATE_2);
        }
    }
    private static class State2 extends StateAbstract {
        @Override
        public void run() {
            if (owner == null) {
                System.out.println("state owner is null");
                return ;
            }
            System.out.println("current state is two");
            this.owner.setState(null);
        }
    }

    
    public static void main(String[] args) {
        StateOwner owner = new StateOwner();
        owner.setState(StateOwner.STATE_1);
        owner.executeByState();
        owner.executeByState();
        owner.executeByState();
    }
}
