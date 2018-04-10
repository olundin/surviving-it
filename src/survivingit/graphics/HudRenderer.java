package survivingit.graphics;

import survivingit.hud.Icon;
import survivingit.hud.ProgressBar;

public interface HudRenderer {

    public void drawProgressBar(ProgressBar progressBar);

    public void drawIcon(Icon icon);

}
