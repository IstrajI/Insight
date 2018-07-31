package com.npgames.insight.data.model;

import java.util.ArrayList;
import java.util.List;

public class TrackingParagraph {
    private int paragraphNumber;
    private List<Action> actions;

    public TrackingParagraph(final int paragraphNumber) {
        this.paragraphNumber = paragraphNumber;
        this.actions = new ArrayList<>();
    }

    public TrackingParagraph(final int paragraphNumber, final List<Action> actions) {
        this.paragraphNumber = paragraphNumber;
        this.actions = actions;
    }

    public int getParagraphNumber() {
        return paragraphNumber;
    }
    public void setParagraphNumber(int paragraphNumber) {
        this.paragraphNumber = paragraphNumber;
    }

    public List<Action> getActions() {
        return actions;
    }
    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Action findOrCreateAction(final Paragraph.ActionTypes actionType) {
        for (Action action : actions) {
            if (action.type == actionType) {
                return action;
            }
        }
        return new Action(actionType);
    }

    public class Action {
        Paragraph.ActionTypes type;
        boolean status;

        public Action(final Paragraph.ActionTypes type) {
            this.type = type;
            this.status = false;
        }

        public Action(final Paragraph.ActionTypes type, final boolean status) {
            this.type = type;
            this.status = status;
        }

        public Paragraph.ActionTypes getType() {
            return type;
        }
        public void setType(Paragraph.ActionTypes type) {
            this.type = type;
        }

        public boolean isStatus() {
            return status;
        }
        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}
