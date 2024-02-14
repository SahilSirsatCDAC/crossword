import { CrosswordService } from './../services/crossword.service';
import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-crossword',
  templateUrl: './crossword.component.html',
  styleUrl: './crossword.component.css',
})
export class CrosswordComponent implements OnInit {
  incorrectWords: any[]= [];
  correctWords: any[]= [];
  incorrectSquaresIds: any[]= [];
  correctSquaresIds: any[]= [];
  inputValues: any[] = [];
  wordData: any;
  words: any[] = [];
  gridCounter: number =0;
  crosswordSize!:number;
  crosswordId!:number;
  constructor(private crosswordService:CrosswordService,private renderer: Renderer2, private el: ElementRef, private toastr: ToastrService){}
  ngOnInit(): void {
    this.crosswordService.getWords(6).subscribe((data) => {
      this.crosswordSize = data[0]?.crossword?.crosswordSize;
      this.crosswordId = data[0]?.crossword?.crosswordId;
      // console.log(data[0]?.crossword?.crosswordSize);
      data.sort(function (a:any, b:any) {
        return a.startRow- b.startRow || a.startColumn- b.startColumn;
    });
    for (let index = 0; index < this.crosswordSize; index++) {
      this.words.push([])
      this.inputValues.push([])
      // console.log(this.words);
    }
    console.log(data);
    data.forEach((word: any) => {
      var fieldsCoveredByWord :string[]=[];
      var startRow:number = word.startRow as number -1;
      var startColumn:number = word.startColumn as number -1;
      var wordLength = word.wordLength;
      var wordLetters: string[] = word.word.split("");
      var orientation = word.orientation;
      // console.log(wordLetters);
      for (let index = 0; index < wordLetters.length; index++) {
        // console.log(startRow);
        // console.log(startColumn+"\n\n\n");
        fieldsCoveredByWord.push(startRow.toString()+startColumn.toString());
        this.words[startRow][startColumn] = wordLetters[index].toUpperCase();
        if (orientation==='H') {
          startColumn++;
        }else if (orientation==='V'){
          startRow++;
        }
      }
      word["fieldsCoveredByWord"]= fieldsCoveredByWord;
    });
    this.wordData=data;
    // console.log(this.words);
    // console.log(this.wordData);
    });
  }
  checkSolutions(){
    if (JSON.stringify(this.inputValues)===JSON.stringify(this.words)){
      this.toastr.success("Everything is correct!", 'Success');

    }else{
      this.toastr.error("Some words are not correct!", 'Try again...');
    }
    var incorrectWords: any[]= [];
    var correctWords: any[]= [];
    var incorrectSquaresIds: any[]= [];
    var correctSquaresIds: any[]= [];
    this.wordData.forEach((word: any) => {
      var correctWord = word.word.toUpperCase();
      var enteredWord: string ="";
      word.fieldsCoveredByWord.forEach((field: any) => {
        var enteredChar = this.inputValues[field[0]][field[1]];
        console.log(enteredChar);
        enteredWord=enteredWord+enteredChar;
      });
      // console.log("correct word: "+correctWord);
      // console.log("entered word: "+enteredWord);
      
      if (correctWord!==enteredWord) {
        incorrectWords.push(word);
        word.fieldsCoveredByWord.forEach((field: any) => {
          incorrectSquaresIds.push(field);
        });
      }else{
        correctWords.push(word);
        word.fieldsCoveredByWord.forEach((field: any) => {
          correctSquaresIds.push(field);
        });
      }
    });
    incorrectSquaresIds.forEach((inputId: any) => {
      // console.log(inputId);
      const inputElement = this.el.nativeElement.querySelector('#i_'+ inputId);
      this.renderer.removeClass(inputElement, 'border-dark-subtle');
      this.renderer.addClass(inputElement, 'border-danger-subtle');
    });
    correctSquaresIds.forEach((inputId: any) => {
      // console.log(inputId);
      const inputElement = this.el.nativeElement.querySelector('#i_'+ inputId);
      this.renderer.removeClass(inputElement, 'border-dark-subtle');
      this.renderer.removeClass(inputElement, 'border-danger-subtle');
      this.renderer.addClass(inputElement, 'border-success');
    });
    this.incorrectWords=incorrectWords;
    this.correctWords=correctWords;
    this.incorrectSquaresIds=incorrectSquaresIds;
    this.correctSquaresIds=correctSquaresIds;
  }
  saveAnswers(){
    this.checkSolutions();
    var correctWordIds :number[]= [];
    // console.log(this.correctWords);
    if (this.correctWords.length!==0) {
      this.correctWords.forEach(word => {
        correctWordIds.push(word.wordId);
      });
      this.crosswordService.saveProgress(correctWordIds, this.crosswordId).subscribe((data: any) => {
        console.log(data);
        this.toastr.success('Crossword Progress Saved...', 'Success');
        
    });
    }
 }
  focusOut(event:any) { 
    // console.log(event);
    var inputElementId :string= event.srcElement.id;
    var rowNumber: number = parseInt(inputElementId[2]);
    var columnNumber: number = parseInt(inputElementId[3]);
    this.inputValues[rowNumber][columnNumber] = event.target.value?.toUpperCase();
    // console.log(this.inputValues[rowNumber][columnNumber]);
    // console.log(this.inputValues);
  }
  showToast(){
    this.toastr.success('Customized toast!', 'Custom Title', {
      closeButton: true, // Show close button
      progressBar: true, // Show progress bar
      progressAnimation: 'increasing', // Progress bar animation
      timeOut: 10000, // Override default timeOut for this toast
      extendedTimeOut: 1000, // Time to close after a user hovers over the toast
      tapToDismiss: false, // Disable dismiss on click
      enableHtml: true, // Enable HTML content in the toast
      toastClass: 'ngx-toastr', // Custom CSS class for the toast
      titleClass: 'toast-title', // Custom CSS class for the title
      messageClass: 'toast-message' // Custom CSS class for the message
    });
  }
}
