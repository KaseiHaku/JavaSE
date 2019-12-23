package kasei.javase.designpattern.behavior.template;

public class FootballGame extends GameTemplate {
    @Override
    void initialize() {
        System.out.println("足球游戏初始化");
    }

    @Override
    void startPlay() {
        System.out.println("足球游戏开始");
    }

    @Override
    void endPlay() {
        System.out.println("足球游戏结束");
    }
}
