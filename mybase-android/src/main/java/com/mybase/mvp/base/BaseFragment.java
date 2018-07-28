package com.mybase.mvp.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.mybase.mvp.animation.ScreenAnimation;
import com.mybase.mvp.interfaces.IBasePresenter;

import java.util.List;

/**
 * Created by nguyenxuanhoi2903 on 21/11/2017.
 */

public abstract class BaseFragment<T> extends AbstractFragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    private T objects;

    public T getObjects() {
        return objects;
    }

    public void setObjects(T objects) {
        this.objects = objects;
    }

    private IBasePresenter mIPresenter;

    public IBasePresenter getmIPresenter() {
        return mIPresenter;
    }

    public void setmIPresenter(IBasePresenter mIPresenter) {
        this.mIPresenter = mIPresenter;
    }

    public static void openFragment(FragmentManager manager,
                                    FragmentTransaction transaction,
                                    Class<? extends BaseFragment> newClass,
                                    ScreenAnimation screenAnimation,
                                    Bundle data,
                                    int containerViewId,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        String tag = newClass.getName();
        BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = newClass.newInstance();
                transaction.setCustomAnimations(screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(), screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
                fragment.setArguments(data);
                transaction.add(containerViewId, fragment, tag);


            } catch (java.lang.InstantiationException  e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            if (fragment.isVisible()) {
                return;
            }
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.show(fragment);
        }
        if (isAddBackStack) {
            transaction.addToBackStack(tag);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public static void openFragment(FragmentManager manager,
                                    FragmentTransaction transaction,
                                    Class<? extends BaseFragment> newClass,
                                    ScreenAnimation screenAnimation,
                                    Bundle data,
                                    int containerViewId,
                                    boolean isCommit) {
        String tag = newClass.getName();
        BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = newClass.newInstance();
                transaction.setCustomAnimations(screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(), screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
                fragment.setArguments(data);
                transaction.add(containerViewId, fragment, tag);


            } catch (java.lang.InstantiationException  e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            if (fragment.isVisible()) {
                return;
            }
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.show(fragment);
        }
        if (manager.getBackStackEntryCount() == 0) {
            transaction.addToBackStack(null);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public static void openFragment(FragmentManager manager,
                                    FragmentTransaction transaction,
                                    BaseFragment fragment,
                                    ScreenAnimation screenAnimation,
                                    int containerViewId,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        String tag = fragment.getClass().getName();
        transaction.setCustomAnimations(
                screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
        transaction.add(containerViewId, fragment, tag);
        if (isAddBackStack) {
            transaction.addToBackStack(tag);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public static void openFragment(FragmentManager manager,
                                    FragmentTransaction transaction,
                                    Fragment fragment,
                                    ScreenAnimation screenAnimation,
                                    int containerViewId,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        String tag = fragment.getClass().getName();
        transaction.setCustomAnimations(
                screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
        transaction.add(containerViewId, fragment, tag);
        if (isAddBackStack) {
            transaction.addToBackStack(tag);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public static void hideFragment(FragmentManager manager, FragmentTransaction transaction,
                                    Class<? extends BaseFragment> classHide,
                                    ScreenAnimation screenAnimation,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        String tag = classHide.getName();
        BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tag);
        if (fragment != null && fragment.isVisible()) {
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.hide(fragment);
            if (isAddBackStack) {
                transaction.addToBackStack(tag);
            }
            if (isCommit) {
                transaction.commit();
            }
        }
    }

    public static void hideFragment(FragmentTransaction transaction,
                                    BaseFragment fragment,
                                    ScreenAnimation screenAnimation,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        if (fragment != null && fragment.isVisible()) {
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.hide(fragment);
            if (isAddBackStack) {
                transaction.addToBackStack(fragment.getClass().getName());
            }
            if (isCommit) {
                transaction.commit();
            }
        }
    }

    public static void removeFragment(FragmentManager manager,
                                      FragmentTransaction transaction,
                                      String tagFragment,
                                      ScreenAnimation screenAnimation,
                                      boolean isAddBackStack,
                                      boolean isCommit) {
        Fragment fragment = manager.findFragmentByTag(tagFragment);
        if (fragment != null) {
            if (screenAnimation != null) {
                transaction.setCustomAnimations(
                        screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                        screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            }
            transaction.remove(fragment);
            if (isAddBackStack) {
                transaction.addToBackStack(tagFragment);
            }
            if (isCommit) {
                transaction.commit();
            }
        }
    }

    public static AbstractFragment getCurrentBaseFragment(FragmentManager manager) {
        List<Fragment> fragments = manager.getFragments();
        if (fragments == null) {
            return null;
        }
        for (int i = fragments.size() - 1; i >= 0; i--) {
            BaseFragment fragment = (BaseFragment) fragments.get(i);
            if (fragment != null && fragment.isVisible()) {
                return fragment;
            }
        }
        return null;

    }

    public static Fragment getCurrentFragment(FragmentManager manager) {
        List<Fragment> fragments = manager.getFragments();
        if (fragments == null) {
            return null;
        }
        for (int i = fragments.size() - 1; i >= 0; i--) {
            Fragment fragment = fragments.get(i);
            if (fragment != null && fragment.isVisible()) {
                return fragment;
            }
        }
        return null;

    }

    public void removeCurrentFragment(String tag) {
        Fragment fragment = BaseFragment.getCurrentBaseFragment(getChildFragmentManager());
        if (fragment != null && fragment.getTag() != tag) {
            BaseFragment.removeFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), fragment.getTag(), ScreenAnimation.NONE, false, true);
        }
    }

    public void hildeCurrentFragment(String tag) {
        Fragment fragment = BaseFragment.getCurrentBaseFragment(getChildFragmentManager());
        if (fragment != null && fragment.getTag() != tag) {
            BaseFragment.removeFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), fragment.getTag(), ScreenAnimation.NONE, false, true);
        }
    }

    public static <T> void openFragmentSendObject(FragmentManager manager,
                                                  FragmentTransaction transaction,
                                                  Class<? extends BaseFragment> newClass,
                                                  ScreenAnimation screenAnimation,
                                                  int containerViewId,
                                                  boolean isAddBackStack,
                                                  boolean isCommit, T t) {
        String tag = newClass.getName();
        BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = newClass.newInstance();
                if (t != null) {
                    fragment.setObjects(t);
                }
                transaction.setCustomAnimations(screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(), screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
                transaction.add(containerViewId, fragment, tag);
            } catch (java.lang.InstantiationException  e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            if (fragment.isVisible()) {
                return;
            }
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.show(fragment);
        }
        if (isAddBackStack) {
            transaction.addToBackStack(tag);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public static <T, F extends IBasePresenter> void openFragmentSendObject(FragmentManager manager,
                                                                            FragmentTransaction transaction,
                                                                            Class<? extends BaseFragment> newClass,
                                                                            ScreenAnimation screenAnimation,
                                                                            int containerViewId,
                                                                            boolean isAddBackStack,
                                                                            boolean isCommit, T t, F f) {
        String tag = newClass.getName();
        BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = newClass.newInstance();
                if (t != null) {
                    fragment.setObjects(t);
                }
                if (f != null) {
                    fragment.setmIPresenter(f);
                }
                transaction.setCustomAnimations(screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(), screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
                transaction.add(containerViewId, fragment, tag);
            } catch (java.lang.InstantiationException  e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            if (fragment.isVisible()) {
                return;
            }
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.show(fragment);
        }
        if (isAddBackStack) {
            transaction.addToBackStack(tag);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public void nextActivity(Class<?> mClass) {
        Intent intent = new Intent(getActivity(), mClass);
        startActivity(intent);
    }

    protected void nextActivityAndKill(Class<?> mClass) {
        nextActivity(mClass);
        getActivity().finish();
    }

    protected void nextActivityForResult(Class<?> mClass, int requestCode) {
        Intent intent = new Intent(getActivity(), mClass);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mIPresenter) {
            mIPresenter.onCancel();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mIPresenter) {
            mIPresenter.onCancel();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mIPresenter) {
            mIPresenter.onCancel();
        }
    }
}
