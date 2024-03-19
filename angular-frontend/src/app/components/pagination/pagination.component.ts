import {Component, ElementRef, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit, OnChanges {

  @Input() currentPage: number = 1;
  @Input() total: number = 0;
  @Input() limit: number = 20;
  @Output() changePage = new EventEmitter<number>();

  pages: number[] = [];
  maxPages: number = 10;
  protected readonly Math = Math;

  constructor(private myElement: ElementRef) { }

  ngOnInit(): void {
    this.updatePages();
  }

  ngOnChanges(changes: SimpleChanges) {
    this.updatePages();
  }


  updatePages(): void {
    const pagesCount = Math.ceil(this.total / this.limit);
    let startPage = Math.max(1, this.currentPage - Math.floor(this.maxPages / 2));
    const endPage = Math.min(pagesCount, startPage + this.maxPages - 1);
    startPage = Math.max(1, endPage - this.maxPages + 1);
    this.pages = this.range(startPage, endPage);
  }

  range(start: number, end: number): number[] {
    return [...Array(end - start + 1).keys()].map((el) => el + start);
  }

}
