package com.gtappdevelopers.gfgroomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class StudentRVAdapter extends ListAdapter<StudentModal, StudentRVAdapter.ViewHolder> {

    private OnItemClickListener listener;

    StudentRVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<StudentModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<StudentModal>() {
        @Override
        public boolean areItemsTheSame(StudentModal oldItem, StudentModal newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(StudentModal oldItem, StudentModal newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getIdentity().equals(newItem.getIdentity()) &&
                    oldItem.getEmail().equals(newItem.getEmail()) &&
                    oldItem.getGender().equals(newItem.getGender()) &&
                    oldItem.getBirth().equals(newItem.getBirth()) &&
                    oldItem.getPaying().equals(newItem.getPaying());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentModal model = getStudentAt(position);
        holder.name.setText(model.getName());
        holder.identity.setText(model.getIdentity());
        holder.email.setText(model.getEmail());
        holder.gender.setText(model.getGender());
        holder.birth.setText(model.getBirth());
        holder.paying.setText(model.getPaying());

    }

    public StudentModal getStudentAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, identity, email,gender,birth,paying;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            identity = itemView.findViewById(R.id.identity);
            email = itemView.findViewById(R.id.email);
            gender = itemView.findViewById(R.id.gender);
            birth = itemView.findViewById(R.id.birth);
            paying = itemView.findViewById(R.id.paying);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(StudentModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}