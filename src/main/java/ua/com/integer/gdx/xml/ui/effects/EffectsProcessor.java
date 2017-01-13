package ua.com.integer.gdx.xml.ui.effects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import ua.com.integer.gdx.xml.ui.XUI;
import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;
import ua.com.integer.gdx.xml.ui.util.ActorMathEval;

public class EffectsProcessor extends XUIProcessor {
    private Actor a;

    @Override
    public void process() {
        a = element.resultActor;

        checkForFloatingEffect();
        checkForMoveUpDownEffect();
        checkForColorChangeEffect();
        checkForSoundClickEffect();
    }

    private void checkForFloatingEffect() {
        if (hasAttribute("floatingEffect")) {
            a.addAction(new FloatingEffectAction(a, ActorMathEval.eval(a, getAttribute("floatingEffect"))));
        }
    }

    private void checkForMoveUpDownEffect() {
        if (hasAttribute("moveUpDown")) {
            float moveInterval = ActorMathEval.eval(a, getAttribute("moveUpDown"));
            float moveTime = 0.1f;
            if (hasAttribute("moveUpDownTime")) {
                moveTime = getFloat("moveUpDownTime");
            }
            a.addAction(Actions.forever(
                    Actions.sequence(
                            Actions.moveBy(0, moveInterval, moveTime),
                            Actions.moveBy(0, -moveInterval, moveTime)
                    )
            ));
        }
    }

    private void checkForColorChangeEffect() {
        if (hasAttribute("changeColorOnClick")) {
            String colorName = getAttribute("changeColorOnClick");
            Color color = Color.GRAY;
            if (!colorName.equals("default")) {
                color = getColor("changeColorOnClick");
            }

            a.addListener(new ColorChangeListener(color));
        }
    }

    private void checkForSoundClickEffect() {
        if (hasAttribute("soundOnClick")) {
            Sound sound = getSound("soundOnClick");
            a.addListener(new SoundClickListener(sound));
        }
    }

    public static void register() {
        XUI.registerXUIProcessors("common", new EffectsProcessor());
    }
}
