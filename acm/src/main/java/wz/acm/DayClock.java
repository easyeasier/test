package wz.acm;

/**
 * Created by wangz on 17-6-24.
 */
public enum DayClock {
    MONDAY(ClockType.WEEKDAY),
    TUESDAY(ClockType.WEEKDAY),
    WEDNESDAY(ClockType.WEEKDAY),
    THURSDAY(ClockType.WEEKDAY),
    FRIDAY(ClockType.WEEKDAY),
    SATURDAY(ClockType.WEEKEND),
    SUNDAY(ClockType.WEEKEND);

    static {
        System.out.println("DayClock init.");
    }
    private final ClockType clockType;

    DayClock(ClockType clockType) {
        this.clockType = clockType;
    }

    public void clock() {
        System.out.println(this.name() + "的闹钟是 ： " + clockType.getClock());
    }

    private enum ClockType {
        WEEKDAY {
            @Override
            String getClock() {
                return "7点";
            }
        },
        WEEKEND {
            @Override
            String getClock() {
                return "9点";
            }
        };

        abstract String getClock();
        static {
            System.out.println("clocktype init.");
        }
    }

    public static void main(String[] args) {
//        for(DayClock dayClock : DayClock.values()){
//            dayClock.clock();
//        }
    }

}
