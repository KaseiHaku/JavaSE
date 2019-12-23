public WorkCalendarUtil {


    /** todo 为当前日期添加指定的工作日天数
     * @param summand 被加日期
     * @param addend 加数
     * @param getEnd 是否返回结果日期的最后一毫秒，如 23:59:59:59:999
     * @return 一个新的 Calendar 实例
     * */
    public static Calendar addNWorkDay(Calendar summand, Integer addend, Boolean getEnd){
        if(DateUtil.isRestDay(summand.getTime())){// 如果传入的日期本身就是节假日
            getEnd = true;
        }

        Calendar result = (Calendar) summand.clone();
        Integer i = 0;
        while (i < addend) {
            result.add(Calendar.DAY_OF_MONTH, 1);
            if(!DateUtil.isRestDay(result.getTime())){
                i++;
            }
        }

        if (getEnd) {
            result = getEndTimeOfDay(result);
        }
        return result;
    }

    /** todo 获取一天中最开始的时间，如： 2018-12-23 00:00:00:000 */
    public static Calendar getStratTimeOfDay(Calendar calendar){
        Calendar startTimeOfDay = Calendar.getInstance();
        startTimeOfDay.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        startTimeOfDay.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        startTimeOfDay.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        startTimeOfDay.set(Calendar.HOUR_OF_DAY, 0);
        startTimeOfDay.set(Calendar.MINUTE, 0);
        startTimeOfDay.set(Calendar.SECOND, 0);
        startTimeOfDay.set(Calendar.MILLISECOND, 0);
        return startTimeOfDay;
    }
    /** todo 获取一天中最后的时间，如： 2018-12-23 23:59:59:999 */
    public static Calendar getEndTimeOfDay(Calendar calendar){
        Calendar endTimeOfDay = Calendar.getInstance();
        endTimeOfDay.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        endTimeOfDay.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        endTimeOfDay.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        endTimeOfDay.set(Calendar.HOUR_OF_DAY, 23);
        endTimeOfDay.set(Calendar.MINUTE, 59);
        endTimeOfDay.set(Calendar.SECOND, 59);
        endTimeOfDay.set(Calendar.MILLISECOND, 999);
        return endTimeOfDay;
    }

    /** todo 判断一个日期是否是休息日 */
    public static Boolean isRestDay(Date date){
        // 根据业务实现
        return false;
    }
}
