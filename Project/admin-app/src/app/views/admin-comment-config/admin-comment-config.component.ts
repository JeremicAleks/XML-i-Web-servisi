import { Component, OnInit } from '@angular/core';
import { RateAndComment } from 'src/app/models/rate-and-comment';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-admin-comment-config',
  templateUrl: './admin-comment-config.component.html',
  styleUrls: ['./admin-comment-config.component.css']
})
export class AdminCommentConfigComponent implements OnInit {

  allComments: Array<RateAndComment>;

  constructor(private commentService: CommentService) { }

  ngOnInit() {
    this.getAllComments();
  }

  approveComment(id: number) {
    console.log('approveComment clicked - id: ' + id);

  }

  getAllComments() {
    console.log('getAllComments');
    this.commentService.getAllComments().subscribe(
      data => {
        alert(data.message);
        this.allComments = data;
      },
      error => {
        alert(error.message);
      }
    );
  }

}
