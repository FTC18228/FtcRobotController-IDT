package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        MeepMeep meepMeep = new MeepMeep(800,120);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(18,18)
                .build();

        myBot.runAction(
            myBot.getDrive().actionBuilder(new Pose2d(-46, -70, Math.toRadians(90)))
                    .setReversed(true)
                    .setTangent(Math.toRadians(90))

                    .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                    .waitSeconds(2)
                    .strafeToLinearHeading(new Vector2d(-5, -53), Math.toRadians(0))
                    .waitSeconds(2)
                    .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                    .waitSeconds(2)
                    .strafeToLinearHeading(new Vector2d(-48, -40), Math.toRadians(90))
                    .waitSeconds(2)
                    .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                    .waitSeconds(2)
                    .strafeToLinearHeading(new Vector2d(-58, -40), Math.toRadians(90))
                    .waitSeconds(2)
                    .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                    .waitSeconds(2)
                    .strafeToLinearHeading(new Vector2d(-68, -40), Math.toRadians(90))
                    .waitSeconds(2)
                    .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                    .waitSeconds(2)
                    .strafeToLinearHeading(new Vector2d(-25, 0), Math.toRadians(0))
                    .waitSeconds(0.25)

                    .build()

        );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}