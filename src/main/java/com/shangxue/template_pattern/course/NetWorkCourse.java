package com.shangxue.template_pattern.course;

/**
 * @program: gupao_pattern_mode
 * @description:
 * @author: zk
 * @create: 2019-04-06 22:38
 **/
public abstract class NetWorkCourse {

    protected final void createCourse(){
        //1、发布预习资料
        this.postPreResource();

        //2、制作PPT课件
        this.createPPT();

        //3、在线直播
        this.liveVideo();

        //4、提交课件、课堂笔记
        this.postNote();

        //5、提交源码
        this.postSource();

        //6、布置作业，有些课是没有作业，有些课是有作业的
        //如果有作业的话，检查作业，如果没作业，完成了
        if(needHomework()){
            checkHomework();
        }
    }

    abstract void checkHomework();

    //钩子方法：实现流程的微调
    protected boolean needHomework(){return false;}

    final void postSource(){
        System.out.println("提交源代码");
    }

    final void postNote(){
        System.out.println("提交课件和笔记");
    }

    final void liveVideo(){
        System.out.println("直播授课");
    }

    final void createPPT(){
        System.out.println("创建备课PPT");
    }

    final void postPreResource(){
        System.out.println("分发预习资料");
    }
}
