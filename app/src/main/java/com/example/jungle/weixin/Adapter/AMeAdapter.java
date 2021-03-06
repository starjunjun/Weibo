package com.example.jungle.weixin.Adapter;

/**
 * Created by jungle on 2017/11/22.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jungle.weixin.Activity.AMeActivity;
import com.example.jungle.weixin.Activity.TotalActivity;
import com.example.jungle.weixin.Activity.WeiboDetailActivity;
import com.example.jungle.weixin.Bean.BaseBean.PicURL;
import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.BaseBean.User;
import com.example.jungle.weixin.PublicUtils.DateUtils;
import com.example.jungle.weixin.PublicUtils.PicUtils;
import com.example.jungle.weixin.PublicUtils.StringUtils;
import com.example.jungle.weixin.PublicUtils.TypeUtils;
import com.example.jungle.weixin.R;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by derrickJ on 2017/11/8.
 */

public class AMeAdapter extends RecyclerView.Adapter<AMeAdapter.ViewHolder> {


    private AMeActivity mContext;
    private List<Status> weiboList;

    class ViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        View weiboHead;
        ImageView avatarImage;
        ImageView identityIcon;
        TextView nickname;
        TextView date;
        TextView time;
        TextView sourceTag;
        TextView source;
        ImageButton moreBtn;

        View weiboMain;

        View bodyView;
        TextView body;

        View singlePicView;
        ImageView singlePic;

        View multiPicsView;
        NineGridView multiPicsGrid;

        View videoView;
        View videoContainerView;
        ImageView video;

        View passageView;
        ImageView passageImage;
        TextView passageTitle;
        TextView passageSubTitle;

        View reweiboView;

        View reweiboBodyView;
        TextView reweiboBody;

        View reweiboSinglePicView;
        ImageView reweiboSinglePic;

        View reweiboMultiPicsView;
        NineGridView reweiboMultiPicsGrid;

        View reweiboVideoView;
        View reweiboVideoContainerView;
        ImageView reweiboVideo;

        View reweiboPassageView;
        ImageView reweiboPassageImage;
        TextView reweiboPassageTitle;
        TextView reweiboPassageSubTitle;

        View weiboFunctionView;
        View likeBtn;
        ImageView likeIcon;
        TextView likeNum;
        View commentBtn;
        ImageView commentIcon;
        TextView commentNum;
        View repostBtn;
        ImageView repostIcon;
        TextView repostNum;

        View view1;

        View transmit_layout;


        public ViewHolder(View view) {
            super(view);

            this.itemView = view;

            weiboHead = (View) view.findViewById(R.id.weibo_head_layout);
            avatarImage = (ImageView) weiboHead.findViewById(R.id.avatar);
            identityIcon = (ImageView) weiboHead.findViewById(R.id.identity_icon);
            nickname = (TextView) weiboHead.findViewById(R.id.nickname);
            date = (TextView) weiboHead.findViewById(R.id.date);
            sourceTag = (TextView) weiboHead.findViewById(R.id.source_tag);
            source = (TextView) weiboHead.findViewById(R.id.source);
            moreBtn = (ImageButton) weiboHead.findViewById(R.id.more_btn);
            moreBtn.setImageResource(R.drawable.down);

            view1 = view;
//weiboMain = view.findViewById(R.id.weibo_main);
//
            bodyView = (View) view.findViewById(R.id.body_layout);
            body = (TextView) bodyView.findViewById(R.id.body);

            singlePicView = (View) view.findViewById(R.id.single_pic_layout);
            singlePic = (ImageView) singlePicView.findViewById(R.id.single_pic);

            multiPicsView = (View) view.findViewById(R.id.multi_pics_layout);
            multiPicsGrid = (NineGridView) multiPicsView.findViewById(R.id.multi_pics_grid);

            videoView = (View) view.findViewById(R.id.video_layout);
            videoContainerView = (View) videoView.findViewById(R.id.video_container);
            video = (ImageView) videoView.findViewById(R.id.video);

            passageView = (View) view.findViewById(R.id.passage_layout);
            passageImage = (ImageView) passageView.findViewById(R.id.passage_image);
            passageTitle = (TextView) passageView.findViewById(R.id.passage_title);
            passageSubTitle = (TextView) passageView.findViewById(R.id.passage_subtitle);

            reweiboView = (View) view.findViewById(R.id.reweibo_layout);

            reweiboBodyView = (View) view.findViewById(R.id.reweibo_body_layout);
            reweiboBody = (TextView) reweiboBodyView.findViewById(R.id.body);

            reweiboSinglePicView = (View) view.findViewById(R.id.reweibo_single_pic_layout);
            reweiboSinglePic = (ImageView) reweiboSinglePicView.findViewById(R.id.single_pic);

            reweiboMultiPicsView = (View) view.findViewById(R.id.reweibo_multi_pics_layout);
            reweiboMultiPicsGrid = (NineGridView) reweiboMultiPicsView.findViewById(R.id.multi_pics_grid);

            reweiboVideoView = (View) view.findViewById(R.id.reweibo_video_layout);
            reweiboVideoContainerView = (View) reweiboVideoView.findViewById(R.id.video_container);
            reweiboVideo = (ImageView) reweiboVideoView.findViewById(R.id.video);

            reweiboPassageView = (View) view.findViewById(R.id.reweibo_passage_layout);
            reweiboPassageImage = (ImageView) reweiboPassageView.findViewById(R.id.passage_image);
            reweiboPassageTitle = (TextView) reweiboPassageView.findViewById(R.id.passage_title);
            reweiboPassageSubTitle = (TextView) reweiboPassageView.findViewById(R.id.passage_subtitle);

            weiboFunctionView = view.findViewById(R.id.weibo_functions_layout);
            likeBtn = weiboFunctionView.findViewById(R.id.like_btn);
            likeIcon = (ImageView) weiboFunctionView.findViewById(R.id.like_image);
            likeNum = (TextView) weiboFunctionView.findViewById(R.id.like_num);
            likeIcon.setImageResource(R.drawable.like_icon);
            commentBtn = weiboFunctionView.findViewById(R.id.comment_btn);
            commentIcon = (ImageView) weiboFunctionView.findViewById(R.id.comment_image);
            commentNum = (TextView) weiboFunctionView.findViewById(R.id.comment_num);
            commentIcon.setImageResource(R.drawable.comment_icon);
            repostBtn = weiboFunctionView.findViewById(R.id.repost_btn);
            repostIcon = (ImageView) weiboFunctionView.findViewById(R.id.repost_image);
            repostNum = (TextView) weiboFunctionView.findViewById(R.id.repost_num);
            repostIcon.setImageResource(R.drawable.repost_icon);


            transmit_layout = view.findViewById(R.id.weibo_transmit_layout);

        }

        private void goneEverything() {
            bodyView.setVisibility(View.GONE);
            singlePicView.setVisibility(View.GONE);
            multiPicsView.setVisibility(View.GONE);
            videoView.setVisibility(View.GONE);
            passageView.setVisibility(View.GONE);
            reweiboView.setVisibility(View.GONE);
            reweiboBodyView.setVisibility(View.GONE);
            reweiboSinglePicView.setVisibility(View.GONE);
            reweiboMultiPicsView.setVisibility(View.GONE);
            reweiboVideoView.setVisibility(View.GONE);
            reweiboPassageView.setVisibility(View.GONE);
            transmit_layout.setVisibility(View.GONE);
        }

    }

    public AMeAdapter(AMeActivity context, List<Status> list) {
        mContext = context;
        weiboList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weibo, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Status status = weiboList.get(position);
        User user = status.getUser();
        Glide.with(mContext).load(user.getProfile_image_url()).into(holder.avatarImage);
        holder.nickname.setText(user.getScreen_name());
        holder.date.setText(DateUtils.formatDate(status.getCreated_at()));
        String source = status.getSource();
        if (source.length() != 0) {
            int start = source.indexOf(">") + 1;
            int end = source.indexOf("</a>");
            holder.source.setText(source.substring(start, end));
        } else {
            holder.sourceTag.setVisibility(View.GONE);
            holder.source.setVisibility(View.GONE);
        }
        holder.repostNum.setText(status.getReposts_count() + "");
        holder.commentNum.setText(status.getComments_count() + "");
        holder.likeNum.setText(status.getAttitudes_count() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WeiboDetailActivity.class);
                intent.putExtra("status", status);
                mContext.startActivity(intent);
                mContext.overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });

        int type = TypeUtils.getStatusType(status);
        Log.i("是你吗？", "onBindViewHolder: " + status.toString());
        switch (type) {
            case 0:
                holder.goneEverything();
                holder.bodyView.setVisibility(View.VISIBLE);
                holder.body.setText(StringUtils.transformWeiboBody(mContext, holder.body, status.getText()));
                break;
            case 1:
                holder.goneEverything();
                holder.bodyView.setVisibility(View.VISIBLE);
                holder.multiPicsView.setVisibility(View.VISIBLE);
                holder.body.setText(StringUtils.transformWeiboBody(mContext, holder.body, status.getText()));
                ArrayList<ImageInfo> imageInfo = new ArrayList<>();
                List<PicURL> urls = status.getPic_urls();
                if (urls != null) {
                    for (PicURL picURL : urls) {
                        ImageInfo info = new ImageInfo();
                        info.setThumbnailUrl(PicUtils.getMiddlePic(picURL.getThumbnail_pic()));
                        info.setBigImageUrl(PicUtils.getOrignal(picURL.getThumbnail_pic()));
                        imageInfo.add(info);
                    }
                }
                holder.multiPicsGrid.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));
                if (urls != null && urls.size() == 1) {
//                    holder.multiPicsGrid.setSingleImageRatio(images.get(0).getWidth() * 1.0f / images.get(0).getHeight());
                    holder.multiPicsGrid.setSingleImageRatio(3.0f / 2);
                } else {
                    holder.multiPicsGrid.setGridSpacing(16);
                }
                break;
            case 5:
                holder.goneEverything();
                holder.bodyView.setVisibility(View.VISIBLE);
                holder.body.setText(StringUtils.transformWeiboBody(mContext, holder.body, status.getText()));
                holder.reweiboView.setVisibility(View.VISIBLE);
                Status restatus = status.getRetweeted_status();
                String addName;
                if (restatus.getUser() != null) {
                    addName = "@" + restatus.getUser().getScreen_name() + " : " + restatus.getText();
                } else {
                    addName = restatus.getText();
                }

                int reType = TypeUtils.getStatusType(restatus);
                switch (reType) {
                    case 0:
                        holder.reweiboBodyView.setVisibility(View.VISIBLE);
                        holder.reweiboBody.setText(StringUtils.transformWeiboBody(mContext, holder.reweiboBody, addName));
                        break;
                    case 1:
                        holder.reweiboBodyView.setVisibility(View.VISIBLE);
                        holder.reweiboMultiPicsView.setVisibility(View.VISIBLE);
                        holder.reweiboBody.setText(StringUtils.transformWeiboBody(mContext, holder.reweiboBody, addName));
                        ArrayList<ImageInfo> reImageInfo = new ArrayList<>();
                        List<String> reUrls = restatus.getPic_ids();
                        if (reUrls != null) {
                            for (String picURL : reUrls) {
                                ImageInfo info = new ImageInfo();
                                info.setThumbnailUrl("https://wx3.sinaimg.cn/thumbnail/" + picURL + ".jgp");
                                info.setBigImageUrl("https://wx3.sinaimg.cn/bmiddle/" + picURL + ".jgp");
                                reImageInfo.add(info);
                            }
                        }
                        holder.reweiboMultiPicsGrid.setAdapter(new NineGridViewClickAdapter(mContext, reImageInfo));
                        if (reUrls != null && reUrls.size() == 1) {
//                    holder.multiPicsGrid.setSingleImageRatio(images.get(0).getWidth() * 1.0f / images.get(0).getHeight());
                            holder.reweiboMultiPicsGrid.setSingleImageRatio(3.0f / 2);
                        } else {
                            holder.reweiboMultiPicsGrid.setGridSpacing(16);
                        }
                        break;
                    case 7:
                        holder.goneEverything();
                        holder.bodyView.setVisibility(View.VISIBLE);
                        holder.multiPicsView.setVisibility(View.VISIBLE);
                        holder.body.setText(StringUtils.transformWeiboBody(mContext, holder.body, status.getText()));
                        ArrayList<ImageInfo> imageInfo1 = new ArrayList<>();
                        List<String> urls1 = restatus.getPic_ids();
                        if (urls1 != null) {
                            for (String picURL : urls1) {
                                ImageInfo info = new ImageInfo();
                                info.setThumbnailUrl("https://wx3.sinaimg.cn/thumbnail/" + picURL + ".jgp");
                                info.setBigImageUrl("https://wx3.sinaimg.cn/bmiddle/" + picURL + ".jgp");
                                imageInfo1.add(info);
                            }
                        }
                        holder.multiPicsGrid.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo1));
                        if (urls1 != null && urls1.size() == 1) {
//                    holder.multiPicsGrid.setSingleImageRatio(images.get(0).getWidth() * 1.0f / images.get(0).getHeight());
                            holder.multiPicsGrid.setSingleImageRatio(3.0f / 2);
                        } else {
                            holder.multiPicsGrid.setGridSpacing(16);
                        }
                        break;
                }
                break;

        }


    }


    @Override
    public int getItemCount() {

        return weiboList.size();

    }


}

