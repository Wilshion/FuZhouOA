package com.wilshion.oa.push;

/**
 * Created by Wilshion on 2018/8/18 21:26.
 * [description : ]
 * [version : 1.0]
 */
public class PushMsgBean {


    /**
     * display_type : notification
     * msg_id : uahnpi2153459873944801
     * body : {"after_open":"go_custom","ticker":"短信测试","custom":{"send_time":"2018-08-18 21:27:04","from_id":1,"user_name":"admin","seq_id":"","content":"短信测试"},"msg_type":"1","text":"短信测试","title":"您有新的微讯请注意查收"}
     * random_min : 0
     */

    private String display_type;
    private String msg_id;
    private BodyBean body;
    private int random_min;

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public int getRandom_min() {
        return random_min;
    }

    public void setRandom_min(int random_min) {
        this.random_min = random_min;
    }

    public static class BodyBean {
        /**
         * after_open : go_custom
         * ticker : 短信测试
         * custom : {"send_time":"2018-08-18 21:27:04","from_id":1,"user_name":"admin","seq_id":"","content":"短信测试"}
         * msg_type : 1
         * text : 短信测试
         * title : 您有新的微讯请注意查收
         */

        private String after_open;
        private String ticker;
        private String msg_type;
        private String text;
        private String title;

        public String getAfter_open() {
            return after_open;
        }

        public void setAfter_open(String after_open) {
            this.after_open = after_open;
        }

        public String getTicker() {
            return ticker;
        }

        public void setTicker(String ticker) {
            this.ticker = ticker;
        }


        public String getMsg_type() {
            return msg_type;
        }

        public void setMsg_type(String msg_type) {
            this.msg_type = msg_type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

       
    }
}
