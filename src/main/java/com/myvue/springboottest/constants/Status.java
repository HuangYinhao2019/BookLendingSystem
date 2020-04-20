package com.myvue.springboottest.constants;/**
 * @description Status
 * @author liusandao
 * @date 2020-4-19 21:39
 */

import java.util.concurrent.TimeUnit;

/**
 * @program: springboottest
 * @description:
 * @author: liusandao
 * @date 2020-04-19 21:39
 */

public abstract  class Status {

    /**
     * 过期时间相关枚举
     */
    public static enum ExpireEnum{
        //未读消息的有效期为30天
        UNREAD_MSG(30L, TimeUnit.DAYS)
        ;

        /**
         * 过期时间
         */
        private Long time;
        /**
         * 时间单位
         */
        private TimeUnit timeUnit;

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}
