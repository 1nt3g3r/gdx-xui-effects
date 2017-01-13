package ua.com.integer.gdx.xml.ui.effects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;

public class FloatingEffectAction extends RepeatAction {
    private float floatDistance;
    private float originalX, originalY;
    private float floatingAngle = MathUtils.random(0f, 360f);

    public FloatingEffectAction(Actor actor, float fDistance) {
        this.originalX = actor.getX();
        this.originalY = actor.getY();
        this.floatDistance = fDistance;
        setCount(RepeatAction.FOREVER);

        firstActorInit(actor);

        setAction(Actions.sequence(
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        floatingAngle += 5f;

                        float dx = floatDistance * MathUtils.sinDeg(floatingAngle);
                        float dy = floatDistance * MathUtils.cosDeg(floatingAngle);

                        float newX = originalX + dx;
                        float newY = originalY + dy;
                        getActor().addAction(Actions.moveTo(newX, newY, 0.09f));
                    }
                }),
                Actions.delay(0.1f)
        ));
    }

    private void firstActorInit(Actor actor) {
        float dx = floatDistance * MathUtils.sinDeg(floatingAngle);
        float dy = floatDistance * MathUtils.cosDeg(floatingAngle);

        float newX = originalX + dx;
        float newY = originalY + dy;

        actor.setPosition(newX, newY);
    }
}
