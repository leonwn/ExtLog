package com.shan.util;

import android.util.Log;

public class ExtLog
{
        static public final Boolean TurnOn = true;
        static public final Boolean TurnOff = false;
        static public final int ASSERT = Log.ASSERT;
        static public final int ERROR = Log.ERROR;
        static public final int WARN = Log.WARN;
        static public final int INFO = Log.INFO;
        static public final int DEBUG = Log.DEBUG;
        static public final int VERBOSE = Log.VERBOSE;
        
        static private final int STACK_TRACE_INDEX = 2;

        public ExtLog(int priority, Boolean enabled)
        {
                if (Log.VERBOSE > priority)
                {
                        m_priority = Log.VERBOSE;
                } else if (Log.ASSERT < priority)
                {
                        m_priority = Log.ASSERT;
                } else
                {
                        m_priority = priority;
                }
                
                m_isDisabled = !enabled;
        }
        
        public void assertTrue(Boolean condition, String format, Object... args)
        {
                if (m_isDisabled)
                        return;
                
                if (!condition)
                {
                        Log.e(makeTag(), makePrefix() + String.format(format, args));
                        throw new AssertionError();                        
                }
        }
        
        public void assertNotNull(Object object, String format, Object... args)
        {
                if (m_isDisabled)
                        return;
                
                if (null == object)
                {
                        String objectName = String.format(format, args);
                        Log.e(makeTag(), makePrefix() + String.format("The %s is NULL!", objectName));
                        throw new AssertionError();
                }
        }

        public int v(String format, Object... args)
        {
                if (m_isDisabled)
                        return 0;
                
                int ret = 0;

                if (Log.VERBOSE >= m_priority)
                {
                        ret = Log.v(makeTag(), makePrefix() + String.format(format, args));                        
                }

                return ret;
        }
        
        public int d(String format, Object... args)
        {
                if (m_isDisabled)
                        return 0;
                
                int ret = 0;

                if (Log.DEBUG >= m_priority)
                {
                        ret = Log.d(makeTag(), makePrefix() + String.format(format, args));
                }

                return ret;
        }
        
        public int i(String format, Object... args)
        {
                if (m_isDisabled)
                        return 0;
                
                int ret = 0;

                if (Log.INFO >= m_priority)
                {
                        ret = Log.i(makeTag(), makePrefix() + String.format(format, args));
                }

                return ret;
        }
        
        public int w(String format, Object... args)
        {
                if (m_isDisabled)
                        return 0;
                
                int ret = 0;

                if (Log.WARN >= m_priority)
                {
                        ret = Log.w(makeTag(), makePrefix() + String.format(format, args));
                }

                return ret;
        }
        
        public int e(String format, Object... args)
        {
                if (m_isDisabled)
                        return 0;
                
                int ret = 0;

                if (Log.ERROR >= m_priority)
                {
                        ret = Log.e(makeTag(), makePrefix() + String.format(format, args));
                }

                return ret;
        }

        private String makeTag()
        {
                String tag = new Throwable().getStackTrace()[STACK_TRACE_INDEX]
                                .getClassName();
                return tag.substring(tag.lastIndexOf('.') + 1);
        }

        private String makePrefix()
        {
                StackTraceElement stack = new Throwable().getStackTrace()[STACK_TRACE_INDEX];
                StringBuffer prefix = new StringBuffer(stack.getMethodName());
                prefix.append("(").append(stack.getLineNumber()).append("): ");
                
                return prefix.toString();
        }

        private int m_priority;
        private Boolean m_isDisabled;
}
