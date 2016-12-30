package presenter;
/**
 * Every presenter which renders model informations to view has to implement this
 * @author Ludwig
 */
public interface RenderPresenterInterface {
    /**
     * Always called if new data from network is available in model and has to be rendered
     */
    void render();
}
