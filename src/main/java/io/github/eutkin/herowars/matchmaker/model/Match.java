package io.github.eutkin.herowars.matchmaker.model;

public class Match  {

    private String enemyByHeroes;
    private String enemyByTitans;

    public Match() {
    }

    public Match(String enemyByHeroes, String enemyByTitans) {
        this.enemyByHeroes = enemyByHeroes;
        this.enemyByTitans = enemyByTitans;
    }

    public Match enemyByHeroes(String name) {
        return new Match(name, this.enemyByTitans);
    }

    public Match enemyByTitans(String name) {
        return new Match(this.enemyByHeroes, name);
    }

    public String getEnemyByHeroes() {
        return enemyByHeroes;
    }

    public String getEnemyByTitans() {
        return enemyByTitans;
    }

    @Override
    public String toString() {
        return "{" +
                "enemyByHeroes='" + enemyByHeroes + '\'' +
                ", enemyByTitans='" + enemyByTitans + '\'' +
                '}';
    }
}
